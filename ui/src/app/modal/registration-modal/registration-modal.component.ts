import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreateUser} from "../../model/user/user";
import {UserService} from "../../service/user-service/user.service";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-registration-modal',
  templateUrl: './registration-modal.component.html',
  styleUrls: ['./registration-modal.component.scss']
})
export class RegistrationModalComponent implements OnInit {

  hide: boolean = true;
  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private dialogRef: MatDialogRef<any>) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.form = this.formBuilder.group({
      'firstName': [null, Validators.required],
      'lastName': [null, Validators.required],
      'email': [null, [Validators.required, Validators.email]],
      'password': [null, [Validators.required]],
      'dateOfBirth': [null, [Validators.required]]
    });
  }

  register() {
    if (this.form.valid) {
      const input: CreateUser = {
        firstName: this.form.value.firstName,
        lastName: this.form.value.lastName,
        password: this.form.value.password,
        email: this.form.value.email,
        dateOfBirth: this.form.value.dateOfBirth,
      };

      this.userService.register(input)
        .subscribe(value => this.dialogRef.close('registered'));
    }
  }

  // checkInUseEmail(control) {
  //   // mimic http database access
  //   let db = ['tony@gmail.com'];
  //   return new Observable(observer => {
  //     setTimeout(() => {
  //       let result = (db.indexOf(control.value) !== -1) ? { 'alreadyInUse': true } : null;
  //       observer.next(result);
  //       observer.complete();
  //     }, 4000)
  //   })
  // }
}
