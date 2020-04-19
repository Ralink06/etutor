import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModalComponent} from "./modal/modal.component";
import {LoginModalComponent} from "./modal/login-modal/login-modal.component";
import {RegistrationModalComponent} from "./modal/registration-modal/registration-modal.component";


const routes: Routes = [{
  path: 'login',
  component: ModalComponent,
  data: {
    component: LoginModalComponent
  },
  outlet: 'modal'
},
  {
    path: 'registration',
    component: ModalComponent,
    data: {
      component: RegistrationModalComponent
    },
    outlet: 'modal'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
