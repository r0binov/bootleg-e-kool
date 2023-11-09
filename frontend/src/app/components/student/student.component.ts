import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {StudentModel} from "../../model/student.model";
import {StudentService} from 'src/app/service/student.service';


@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  constructor(private studentService: StudentService) {
  }

  isTableVisible = false;
  expandRow = false;
  students: StudentModel[] = [];
  newStudent: StudentModel = new StudentModel(0, '', '');
  selectedStudent: StudentModel | null = null;
  isEdit = false;

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
          this.scrollToElement.nativeElement.scrollIntoView({behavior: 'smooth'});
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
    this.studentService.getStudents().subscribe({
      next: (students: StudentModel[]) => {
        this.students = students;
      },
      error: (error) => {
        console.error('Error fetching students:', error);
      }
    })
  }

  addStudent(): void {
    this.studentService.addStudent(this.newStudent).subscribe({
      next: () => {
        this.getStudents();
        this.newStudent = new StudentModel(0, '', '');
      }
    });
  }

  updateStudent(): void {
    if (this.selectedStudent) {
      this.studentService.updateStudent(this.selectedStudent).subscribe({
        next: () => {
          this.selectedStudent = null;
          this.expandRow = false;
          this.getStudents();
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
