import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MandateCoursesComponent } from './mandate-courses.component';

describe('MandateCoursesComponent', () => {
  let component: MandateCoursesComponent;
  let fixture: ComponentFixture<MandateCoursesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MandateCoursesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MandateCoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
