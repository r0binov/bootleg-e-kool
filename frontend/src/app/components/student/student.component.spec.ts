import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GradeComponent } from './student.component';

describe('GradeComponent', () => {
  let component: StudentComponent;
  let fixture: ComponentFixture<GradeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GradeComponent]
    });
    fixture = TestBed.createComponent(GradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
