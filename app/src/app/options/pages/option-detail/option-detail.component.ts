import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {BuyOption} from "../../model/buy-option";
import {switchMap} from "rxjs/operators";
import {BuyOptionService} from "../../model/buy-option.service";

@Component({
  selector: 'app-option-detail',
  templateUrl: './option-detail.component.html'
})
export class OptionDetailComponent implements OnInit {

  option: BuyOption;

  constructor(private optionService: BuyOptionService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.loadBuyOption()
  }

  private loadBuyOption() {
    this.route.paramMap.pipe(
      switchMap(params => this.optionService.getById(+params.get('id')))
    ).subscribe(
      option => {
        this.option = option;
      },
      error => {
        console.log(error);
      }
    );
  }

}
