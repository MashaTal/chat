import { TestBed } from '@angular/core/testing';

import { ResponceProcessingService } from './responce-processing.service';

describe('ResponceProcessingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ResponceProcessingService = TestBed.get(ResponceProcessingService);
    expect(service).toBeTruthy();
  });
});
