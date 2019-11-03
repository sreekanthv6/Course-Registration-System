export class User {
    firstName: String;
    lastName: String;
    email: String;
    deptId: String;
    mobile: number; 
    password: String;
    id: String;
    userExists: Boolean;
    validUser: Boolean;
    role: number;
    constructor() {
        this.firstName = '';
        this.lastName = '';
        this.email='';
        this.deptId='';
        this.password = '';
        this.id = '';
        this.userExists = false;
        this.validUser = false;
        this.role = 0;
    }
}
