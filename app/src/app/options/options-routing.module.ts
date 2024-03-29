import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OptionDetailComponent} from "./pages/option-detail/option-detail.component";
import {DealDetailComponent} from "../deals/pages/deal-detail/deal-detail.component";

const routes: Routes = [
  {path: '', component: OptionDetailComponent},
  {path: 'deals/view/:id', component: DealDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OptionsRoutingModule { }
