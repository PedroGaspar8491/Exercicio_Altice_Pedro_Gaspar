import { TestBed } from '@angular/core/testing';

import { LabsecService } from './labsec.service';

describe('LabsecService', () => {
  let service: LabsecService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LabsecService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
