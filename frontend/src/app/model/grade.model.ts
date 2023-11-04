import {StudentModel} from "./student.model";
import {SubjectModel} from "./subject.model";

export class GradeModel {
  constructor(
    public gradeId: number,
    public gradeValue: number,
    public student: StudentModel,
    public subject: SubjectModel,
  ) {
  }
}
