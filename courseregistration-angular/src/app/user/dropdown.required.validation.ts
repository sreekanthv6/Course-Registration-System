import {AbstractControl} from '@angular/forms';
export class DropdownValidation {

    static DeptRequired(AC: AbstractControl) {
       let deptId = AC.get('deptId').value; // to get value in input tag
       //let confirmPassword = AC.get('confirmPassword').value; // to get value in input tag
        if(deptId === '') {
            AC.get('deptId').setErrors( {DeptRequired: true} )
        } else {
            return null
        }
    }
}