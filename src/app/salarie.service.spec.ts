import { TestBed } from '@angular/core/testing';

import { SalarieService } from './salarie.service';

describe('SalarieService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalarieService = TestBed.get(SalarieService);
    expect(service).toBeTruthy();
  });
});
