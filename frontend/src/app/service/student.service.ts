import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {GradeModel} from "../model/grade.model";
import {GradeDTOModel} from "../model/gradeDTO.model";
import {UpdateGradeDTOModel} from "../model/updateGradeDTO.model";
import {Observable} from "rxjs";
import {StudentModel} from "../model/student.model";

@Injectable()
export class StudentService {
  private apiUrl = "http://localhost:8080/api/v1/ekool/student"
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
  });
  constructor(private http: HttpClient) {
  }


  getStudentsFromApi = (): Observable<StudentModel[]> => {
    return this.http.get<StudentModel[]>(this.apiUrl + "/");
  }

  postStudentToApi = (student: StudentModel) => {
    return this.http.post(this.apiUrl + "/addStudent", student, {headers: this.headers});
  }

  updateStudentInApi = (student: StudentModel) => {
    return this.http.put((this.apiUrl + "/updateGrade"), student, {headers: this.headers});
  }

  deleteStudentInApi = (id: number): Observable<any> => {
    return this.http.delete((this.apiUrl + `/deleteGrade/${id}`), {headers: this.headers});
  }
}
