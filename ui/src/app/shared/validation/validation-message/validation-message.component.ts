import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl} from "@angular/forms";

@Component({
  selector: '[app-validation-message]',
  templateUrl: './validation-message.component.html',
  styleUrls: ['./validation-message.component.scss']
})
export class ValidationMessageComponent {
  @Input() control: AbstractControl;

  get errorMessageFromFormControl(): string {
    if (this.control) {
      for (const propertyName in this.control.errors) {
        if (this.control.errors.hasOwnProperty(propertyName) && this.control.touched) {
          return this.getValidatorErrorMessage(propertyName, this.control.errors[propertyName]);
        }
      }
    }
    return null;
  }

  getValidatorErrorMessage(validatorName: string, validatorValue?: any) {
    const config = {
      'required': 'This field is required',
      'email': 'Invalid email address',
      'minlength': `Minimum length  ${validatorValue != null ? validatorValue.requiredLength : 1}`,
      'maxlength': `Maximum length  ${validatorValue != null ? validatorValue.requiredLength : 1}`,
      'minValue': `Minimum value  ${validatorValue != null ? validatorValue.requiredValue : 0}`,
      'valueLowerThanPeopleGot': `Value can not be lower than people got`,
      'notFuture': `The date must be in future`,
      'emailUsed': `This email is already used`
    };

    return config[validatorName];
  }

}
