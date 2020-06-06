import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ListePrimeComponent } from './liste-prime.component';

describe('ListePrimeComponent', () => {
  let component: ListePrimeComponent;
  let fixture: ComponentFixture<ListePrimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListePrimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListePrimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
