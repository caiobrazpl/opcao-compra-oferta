import {Component, OnInit} from "@angular/core";
import {Deal} from "../model/deal";
import {DealService} from "../model/deal.service";
import {BuyOption} from "../../options/model/buy-option";

@Component({
  selector: 'app-deal-list',
  templateUrl: './deal-list.component.html',
  styleUrls: ['./deal-list.component.scss']
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

  minValue(deal: Deal): number {
    if (deal.buyOptions) {

      let option: BuyOption = deal.buyOptions.reduce((prev, curr) => {
        return prev.salePrice < curr.salePrice ? prev : curr;
      });

      if (option) {
        return option.salePrice;
      }
    }

    return null;
  }
}
