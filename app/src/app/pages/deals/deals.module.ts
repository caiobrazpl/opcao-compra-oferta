import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DealsRoutingModule} from './deals-routing.module';
import {DealListComponent} from './deal-list/deal-list.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [DealListComponent],
  imports: [
    CommonModule,
    DealsRoutingModule,
    ReactiveFormsModule
  ]
})
export class DealsModule {
}
