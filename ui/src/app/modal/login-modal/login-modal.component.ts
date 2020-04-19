import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user-service/user.service";
import { UserLogin} from "../../model/user/user";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.scss']
})
export class LoginModalComponent implements OnInit {

  hide: boolean = true;
  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private dialogRef: MatDialogRef<any>) {
  }


  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.form = this.formBuilder.group({
      'email': [null, [Validators.required, Validators.email]],
      'password': [null, [Validators.required]]
    });
  }

  login() {
    if (this.form.valid) {
      const input: UserLogin = {
        password: this.form.value.password,
        username: this.form.value.email,
      };

      this.userService.login(input)
        .subscribe(value => this.dialogRef.close('logged'));
    }
  }
}
