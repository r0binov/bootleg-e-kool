import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {GradeModel} from "../model/grade.model";

@Injectable()
export class GradeService {
  private apiUrl = "http://localhost:8080/api/v1/ekool/grade"
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
  });
  constructor(private http: HttpClient) {
  }

  getLogsFromApi = () => {
    return this.http.get<GradeModel[]>(this.apiUrl + "/");
  }

  postLogsToApi = (studentId: number, subjectId: number, gradeValue: number) => {
    return this.http.post(this.apiUrl + "/addGrade", {studentId, subjectId, gradeValue}, {headers: this.headers});
  }

  editLogInApi = (gradeId: number, gradeValue: number) => {
    return this.http.put((this.apiUrl + "/updateGrade"), {gradeId, gradeValue}, {headers: this.headers});
  }

  deleteLogInApi = (id: number) => {
    return this.http.delete((this.apiUrl + `/deleteGrade/${id}`), {headers: this.headers});
  }
}
