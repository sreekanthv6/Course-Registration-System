import { TestBed } from '@angular/core/testing';

import { MyclassesService } from './myclasses.service';

describe('MyclassesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MyclassesService = TestBed.get(MyclassesService);
    expect(service).toBeTruthy();
  });
});
