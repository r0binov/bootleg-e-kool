import {StudentModel} from "./student.model";
import {SubjectModel} from "./subject.model";

export class GradeModel {
  constructor(
    public id: number,
    public gradeValue: number,
    public student: StudentModel,
    public subject: SubjectModel,
  ) {
  }
}
