import {Component, OnInit} from "@angular/core";
import {Deal} from "../../model/deal";
import {DealService} from "../../model/deal.service";
import {ActivatedRoute, Router} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {BuyOption} from "../../../options/model/buy-option";

@Component({
  selector: 'app-deal-detail',
  templateUrl: './deal-detail.component.html'
})
export class DealDetailComponent implements OnInit {

  deal: Deal;
  buyOptions: BuyOption[] = [];

  constructor(private dealService: DealService,
              private route: ActivatedRoute,
              private router: Router,) {
  }

  ngOnInit(): void {
    this.loadDeal();
  }

  private loadDeal() {
    this.route.paramMap.pipe(
      switchMap(params => this.dealService.getById(+params.get('id')))
    ).subscribe(
      deal => {
        this.deal = deal;
      },
      error => {
        console.log(error);
      }
    );
  }

  viewBuyOption(option: BuyOption) {
    this.router.navigateByUrl('deals/buyOptions/view/' + option.id + '?idDeal=' + this.deal.id).then();
  }

  newBuyOption() {
    this.router.navigateByUrl('deals/buyOptions/new?idDeal=' + this.deal.id).then();
  }

}
