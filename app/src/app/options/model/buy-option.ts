import {Deal} from "../../deals/model/deal";

export class BuyOption {

  id: number;
  title: string;
  text: string;
  normalPrice: number;
  salePrice: number;
  percentageDiscount: number;
  quantityCoupon: number;
  startDate: string;
  endDate: string;
  deal: Deal;

  constructor(props: BuyOption) {
    Object.assign(this, props)
  }
}
