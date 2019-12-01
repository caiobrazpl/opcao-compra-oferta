import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DealListComponent} from './deal-list/deal-list.component';

const routes: Routes = [
  {path: '', component: DealListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealsRoutingModule { }
