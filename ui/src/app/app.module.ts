import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderComponent} from './main/header/header.component';
import {ModalComponent} from './modal/modal.component';
import {LoginModalComponent} from './modal/login-modal/login-modal.component';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from "@angular/material/dialog";
import {RouterModule} from "@angular/router";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {RegistrationModalComponent} from './modal/registration-modal/registration-modal.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {UserService} from "./service/user-service/user.service";
import {ReactiveFormsModule} from "@angular/forms";
import {ValidationMessageComponent} from './shared/validation/validation-message/validation-message.component';
import {ValidationFormDirective} from './shared/validation/validation-form/validation-form.directive';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {NotificationService} from "./service/notification-service/notification.service";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {ProfileComponent} from './main/user/profile/profile.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {HttpInterceptorService} from "./interceptor/http-interceptor.service";
import { HomeComponent } from './main/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ModalComponent,
    LoginModalComponent,
    RegistrationModalComponent,
    ValidationMessageComponent,
    ValidationFormDirective,
    ProfileComponent,
    HomeComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatProgressBarModule
  ],
  entryComponents: [LoginModalComponent, RegistrationModalComponent],
  providers: [
    NotificationService,
    UserService,
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
