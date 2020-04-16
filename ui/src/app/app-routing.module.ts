import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModalComponent} from "./modal/modal.component";
import {LoginModalComponent} from "./modal/login-modal/login-modal.component";


const routes: Routes = [{
  path: 'login',
  component: ModalComponent,
  data: {
    component: LoginModalComponent
  },
  outlet: 'modal'
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
