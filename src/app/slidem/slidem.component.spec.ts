import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlidemComponent } from './slidem.component';

describe('SlidemComponent', () => {
  let component: SlidemComponent;
  let fixture: ComponentFixture<SlidemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlidemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlidemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
