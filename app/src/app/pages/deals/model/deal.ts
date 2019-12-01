import {BuyOption} from "../../options/model/buy-option";

export class Deal {

  constructor(public id: number,
              public title: string,
              public text: string,
              public createDate: string,
              public publishDate: string,
              public endDate: string,
              public url: string,
              public totalSold: number,
              public type: DealType,
              public buyOptions: BuyOption[]) {
  }

}

enum DealType {
  LOCAL = "Local",
  PRODUCT = "Produto",
  TRAVEL = "Viagem"
}
