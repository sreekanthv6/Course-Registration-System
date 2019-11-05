import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayBillComponent } from './pay-bill.component';

describe('PayBillComponent', () => {
  let component: PayBillComponent;
  let fixture: ComponentFixture<PayBillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayBillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayBillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
