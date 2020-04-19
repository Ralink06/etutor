import { Injectable } from '@angular/core';
import {FormArray, FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { }

  markInvalidAsTouched(form: FormGroup | FormArray) {
    Object.keys(form.controls)
      .map(name => form.controls[name])
      .forEach(control => {
        control.markAsTouched();
        control.updateValueAndValidity();
        if (control instanceof FormGroup || control instanceof FormArray) {
          this.markInvalidAsTouched(control);
        }
      });
  }
}
