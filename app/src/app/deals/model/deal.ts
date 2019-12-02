import {BuyOption} from "../../options/model/buy-option";

export class Deal {

  public id: number;
  public title: string;
  public text: string;
  public createDate: string;
  public publishDate: string;
  public endDate: string;
  public url: string;
  public totalSold: number;
  public type: DealType;
  public buyOptions: BuyOption[];

  constructor(params: Deal) {
    Object.assign(this, params);
  }

  get minValue(): string {
    if (this.buyOptions && this.buyOptions.length > 0) {

      let option: BuyOption = this.buyOptions.reduce((prev, curr) => {
        return prev.salePrice < curr.salePrice ? prev : curr;
      });

      return Number(option.salePrice).toFixed(2).toString().replace('.', ',');
    }

    return '';
  }

}

enum DealType {
  LOCAL = "Local",
  PRODUCT = "Produto",
  TRAVEL = "Viagem"
}
