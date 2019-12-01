import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DealListComponent} from './pages/deal-list/deal-list.component';
import {DealDetailComponent} from "./pages/deal-detail/deal-detail.component";
import {DealFormComponent} from "./pages/deal-form/deal-form.component";
import {OptionDetailComponent} from "../options/pages/option-detail/option-detail.component";
import {OptionFormComponent} from "../options/pages/optrion-form/option-form.component";

const routes: Routes = [
  {path: '', component: DealListComponent},
  {path: 'view/:id', component: DealDetailComponent},
  {path: 'new', component: DealFormComponent},
  {path: 'buyOptions/view/:id', component: OptionDetailComponent},
  {path: 'buyOptions/new', component: OptionFormComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealsRoutingModule { }
