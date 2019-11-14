export class Course {
    courseId: String;
    courseName: String;
    deptId: String;
    semester: String;
    year: Number; 
    isActive: Boolean;
    amount: Number;
    isManadatory: Boolean;
    strength: Number;
    degree: String;
    professor: String;
    courseMaNumberxStrength: Number;
    courseTimings: String;
    isAddedtoCart: Boolean;
    startDate: String;
    endDate: String;
    days: String;
    constructor() {
        this.courseId = '';
        this.courseName = '';
        this.deptId='';
        this.year=0;
        this.amount = 0;
        this.strength=0;
        this.isActive = false;
        this.isManadatory = false;
        this.degree = '';
        this.professor='';
        this.courseMaNumberxStrength=0;
        this.courseTimings='';
        this.isAddedtoCart=false;
        this.startDate='';
        this.endDate='';
    }
}
