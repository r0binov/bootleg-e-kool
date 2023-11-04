import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {GradeService} from "../../service/grade.service";
import {GradeModel} from "../../model/grade.model";
import {GradeDTOModel} from "../../model/gradeDTO.model";
import {UpdateGradeDTOModel} from "../../model/updateGradeDTO.model";
import {StudentModel} from "../../model/student.model";
import {SubjectModel} from "../../model/subject.model";


@Component({
  selector: 'app-grade',
  templateUrl: './grade.component.html',
  styleUrls: ['./grade.component.css']
})
export class GradeComponent implements OnInit {
  constructor(private gradeService: GradeService) {
  }

  isTableVisible = false;
  expandRow = false;
  grades: GradeModel[] = [];
  newGrade: GradeDTOModel = new GradeDTOModel(0, 0, 0); // Initialize with default values
  selectedGrade: GradeModel | null = null;
  updatedGradeValue: number = 0;
  isEdit = false;

  subjects: SubjectModel[] = [];
  students: StudentModel[] = [];

  @ViewChild('scrollToElement') scrollToElement?: ElementRef;

  ngOnInit(): void {
    this.getAllGrades();
  }

  @HostListener('window:resize', ['$event'])
  onWindowResize(event: any): void {
    this.checkScreenWidth();
  }

  checkScreenWidth(): void {
    this.isTableVisible = window.innerWidth > 630;
  }

  onEditClickScrollToElement(): void {
    if (window.innerWidth < 630) {
      setTimeout(() => {
        if (this.scrollToElement) {
          this.scrollToElement.nativeElement.scrollIntoView({behavior: 'smooth'});
        }
      }, 100);
    }
  }

  cancelAddGrade(): void {
    this.expandRow = false;
  }

  editGrade(grade: GradeModel): void {
    this.selectedGrade = grade;
    this.isEdit = true;
  }

  cancelEditedGrade(): void {
    // Reset the properties without saving changes
    this.selectedGrade = null;
  }

  getAllGrades(): void {
    this.gradeService.getGradesFromApi().subscribe({
      next: (data: GradeModel[]) => {
        this.grades = data;
      },
      error: (error) => {
        console.error(`Error fetching grades ${error}`)
      }
    })
  }

  addGrade(): void {
    this.gradeService.postGradeToApi(this.newGrade).subscribe({

      next: () => {
        this.getAllGrades();
        this.newGrade = new GradeDTOModel(0,0,0)
      },
      error: (error) => {
        console.error('Error occured in adding grade:', error);
      }
    });
  }

  updateGrade(): void {
    if (this.selectedGrade) {
      const updateGradeDTO = new UpdateGradeDTOModel(
        this.selectedGrade.gradeId,
        this.selectedGrade.gradeValue
      );

      this.gradeService.updateGradeInApi(updateGradeDTO).subscribe(
        () => {
          this.isEdit = false;
          this.selectedGrade = null;
          this.getAllGrades(); // Refresh the grade list after updating
        },
        (error) => {
          console.error('Error updating grade:', error);
        }
      );
    }
  }

  deleteGrade(gradeId: number): void {
    if (confirm('Are you sure you want to delete this grade?')) {
      this.gradeService.deleteGradeInApi(gradeId).subscribe({
        next: () => {
          this.getAllGrades();
        }
        ,
        error: (error) => {
          console.error('Error deleting log:', error);
        }
      })
    }
  }
}
