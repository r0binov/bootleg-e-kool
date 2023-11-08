import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {GradeService} from "../../service/grade.service";
import {GradeModel} from "../../model/grade.model";
import {GradeDTOModel} from "../../model/gradeDTO.model";
import {UpdateGradeDTOModel} from "../../model/updateGradeDTO.model";
import {StudentModel} from "../../model/student.model";
import {SubjectModel} from "../../model/subject.model";
import { StudentService } from 'src/app/service/student.service';


@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  constructor(private studentService: StudentService) {}

  isTableVisible = false;
  expandRow = false;
  students: StudentModel[] = [];
  newStudent: StudentModel = new StudentModel(0, '', ''); // Initialize with default values
  selectedStudent: StudentModel | null = null;
  isEdit = false;
  sortColumn: string | null = null;
  isAscending = true;

  @ViewChild('scrollToElement') scrollToElement?: ElementRef;

  ngOnInit(): void {
    this.getStudents();
    this.checkScreenWidth();
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
          this.scrollToElement.nativeElement.scrollIntoView({ behavior: 'smooth' });
        }
      }, 100);
    }
  }

  cancelAddStudent(): void {
    this.expandRow = false;
  }

  editStudent(student: StudentModel): void {
    this.selectedStudent = student;
    this.isEdit = true;
  }

  cancelEditedStudent(): void {
    this.selectedStudent = null;
  }

  getStudents() {
    this.studentService.getStudents().subscribe(
      (students: StudentModel[]) => {
        this.students = students;
      },
      (error) => {
        console.error('Error fetching students:', error);
      }
    );
  }

  addStudent(): void {
    this.studentService.addStudent(this.newStudent).subscribe({
      next: () => {
        this.getStudents(),
        this.newStudent = new StudentModel (0,'','')
      }
    });
  }

  updateStudent(): void {
    if (this.selectedStudent) {
      this.studentService.updateStudent(this.selectedStudent).subscribe({
        next: () => {
          this.selectedStudent = null,
          this.expandRow = false,
          this.getStudents()
        }
      });
    }
  }

  deleteStudent(studentId: number): void {
    if (confirm('Are you sure you want to delete this student?')) {
      this.studentService.deleteStudent(studentId).subscribe({
        next: () => {
          this.getStudents();
        },
        error: (error) => {
          console.error('Error deleting student:', error);
        }
      });
    }
  }
}