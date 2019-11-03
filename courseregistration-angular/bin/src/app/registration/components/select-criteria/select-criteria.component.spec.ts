import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectCriteriaComponent } from './select-criteria.component';

describe('SelectCriteriaComponent', () => {
  let component: SelectCriteriaComponent;
  let fixture: ComponentFixture<SelectCriteriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectCriteriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectCriteriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
