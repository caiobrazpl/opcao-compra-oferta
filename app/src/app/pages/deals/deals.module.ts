import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DealsRoutingModule} from './deals-routing.module';
import {DealListComponent} from './deal-list/deal-list.component';
import {ReactiveFormsModule} from '@angular/forms';
import {CurrencyMaskModule} from "ng2-currency-mask";
import {DealDetailComponent} from "./deal-detail/deal-detail.component";
import {DealFormComponent} from "./deal-form/deal-form.component";
import {OptionDetailComponent} from "../options/option-detail/option-detail.component";

@NgModule({
  declarations: [
    DealListComponent,
    DealDetailComponent,
    DealFormComponent,
    OptionDetailComponent
  ],
  imports: [
    CommonModule,
    DealsRoutingModule,
    ReactiveFormsModule,
    CurrencyMaskModule
  ]
})
export class DealsModule {
}
