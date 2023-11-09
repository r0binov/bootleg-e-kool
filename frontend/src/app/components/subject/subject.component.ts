import {Component, OnInit} from '@angular/core';
import {SubjectModel} from "../../model/subject.model";
import {SubjectService} from "../../service/subject.service";

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {
  subjects: SubjectModel[] = [];
  newSubject: SubjectModel = new SubjectModel(0, '');
  selectedSubject: SubjectModel | null = null;

  expandRow = false
  isEdit = false;

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.getSubjects();
  }

  cancelAddSubject(): void {
    this.expandRow = false;
  }

  editSubject(subject: SubjectModel): void {
    this.selectedSubject = subject;
    this.isEdit = true;
  }

  cancelEditedSubject(): void {
    this.selectedSubject = null;
  }

  getSubjects() {
    this.subjectService.getSubjectFromApi().subscribe((data) => {
      this.subjects = data;
    });
  }

  addSubject() {
    this.subjectService.postSubjectToApi(this.newSubject).subscribe({
      next: () => {
        this.getSubjects();
        this.newSubject = new SubjectModel(0, '');
        this.expandRow = false
      },
      error: (error) => {
        alert(error)
      }
    });
  }

  updateSubject(subject: SubjectModel) {
    this.subjectService.updateSubjectInApi(subject).subscribe(() => {
      this.getSubjects();
      this.isEdit = false;
      this.selectedSubject = new SubjectModel(0, '')
    });
  }

  deleteSubject(id: number) {
    this.subjectService.deleteSubjectInApi(id).subscribe(() => {
      this.getSubjects();
    });
  }
}
