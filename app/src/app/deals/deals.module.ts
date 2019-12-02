import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DealsRoutingModule} from './deals-routing.module';
import {DealListComponent} from './pages/deal-list/deal-list.component';
import {ReactiveFormsModule} from '@angular/forms';
import {CurrencyMaskModule} from "ng2-currency-mask";
import {DealDetailComponent} from "./pages/deal-detail/deal-detail.component";
import {DealFormComponent} from "./pages/deal-form/deal-form.component";
import {OptionDetailComponent} from "../options/pages/option-detail/option-detail.component";
import {OptionFormComponent} from "../options/pages/optrion-form/option-form.component";

@NgModule({
  declarations: [
    DealListComponent,
    DealDetailComponent,
    DealFormComponent,
    OptionDetailComponent,
    OptionFormComponent
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
