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

  grades: GradeModel[] = [];
  newGrade: GradeDTOModel = new GradeDTOModel(0, 0, 0);
  selectedGrade: GradeModel | null = null;

  isEdit = false;
  isTableVisible = false;
  expandRow = false;

  subjects: SubjectModel[] = [];
  students: StudentModel[] = [];

  sortColumn: string | null = null;
  isAscending = true;

  @ViewChild('scrollToElement') scrollToElement?: ElementRef;

  ngOnInit(): void {
    this.getAllGrades();
    this.getStudents();
    this.getSubjects();
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
    this.selectedGrade = null;
  }

  sortTable(columnName: string) {
    if (this.sortColumn === columnName) {
      // If already sorted by the clicked column, toggle the sorting order
      this.isAscending = !this.isAscending;
    } else {
      // If sorting a new column, set the sorting column and order
      this.sortColumn = columnName;
      this.isAscending = true;
    }

    // Sort the grades array based on the selected column
    this.grades.sort((a, b) => {
      const aValue = this.getColumnValue(a, columnName);
      const bValue = this.getColumnValue(b, columnName);

      if (aValue < bValue) {
        return this.isAscending ? -1 : 1;
      } else if (aValue > bValue) {
        return this.isAscending ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  getColumnValue(obj: any, column: string): any {
    const keys = column.split('.');
    return keys.reduce((o, key) => (o && o[key]) || null, obj);
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

  getSubjects() {
    this.gradeService.getSubjectsFromApi().subscribe({
      next: (subjects: SubjectModel[]) => {
        this.subjects = subjects;
      },
      error: (error) => {
        console.error('Error fetching subjects:', error);
      }
    });
  }

  getStudents() {
    this.gradeService.getStudentsFromApi().subscribe({
      next: (students: StudentModel[]) => {
        this.students = students;
      },
      error: (error) => {
        console.error('Error fetching students:', error);
      }
    });
  }

  addGrade(): void {
    this.gradeService.postGradeToApi(this.newGrade).subscribe({

      next: () => {
        this.getAllGrades();
        this.newGrade = new GradeDTOModel(0, 0, 0);
        this.expandRow = false;
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

      this.gradeService.updateGradeInApi(updateGradeDTO).subscribe({
        next: () => {
          this.selectedGrade = null;
          this.isEdit = false;
          this.getAllGrades();
        }
        ,
        error: (error) => {
          console.error('Error updating grade:', error);
        }
      });
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
