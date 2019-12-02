import {Component, OnInit} from "@angular/core";
import {Deal} from "../../model/deal";
import {DealService} from "../../model/deal.service";

@Component({
  selector: 'app-deal-list',
  templateUrl: './deal-list.component.html'
})
export class DealListComponent implements OnInit{

  deals: Deal[] = [];

  constructor(private dealService: DealService) {
  }

  ngOnInit(): void {
    this.dealService.listAll().subscribe(value => {
      this.deals = value;
    })
  }

}
