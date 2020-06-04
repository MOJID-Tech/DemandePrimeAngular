import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandePrimeComponent } from './demande-prime.component';

describe('DemandePrimeComponent', () => {
  let component: DemandePrimeComponent;
  let fixture: ComponentFixture<DemandePrimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemandePrimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandePrimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
