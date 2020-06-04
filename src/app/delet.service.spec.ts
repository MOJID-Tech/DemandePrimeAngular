import { TestBed } from '@angular/core/testing';

import { DeletService } from './delet.service';

describe('HelloWordService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeletService = TestBed.get(DeletService);
    expect(service).toBeTruthy();
  });
});
