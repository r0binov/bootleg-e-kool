import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {GradeModel} from "../model/grade.model";
import {GradeDTOModel} from "../model/gradeDTO.model";
import {UpdateGradeDTOModel} from "../model/updateGradeDTO.model";
import {Observable} from "rxjs";
import {SubjectModel} from "../model/subject.model";
import {StudentModel} from "../model/student.model";

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

  getGradesFromApi = (): Observable<GradeModel[]> => {
    return this.http.get<GradeModel[]>(this.apiUrl + "/");
  }

  getSubjectsFromApi = (): Observable<SubjectModel[]> => {
    return this.http.get<SubjectModel[]>("http://localhost:8080/api/v1/ekool/subject/");
  }

  getStudentsFromApi = (): Observable<StudentModel[]> => {
    return this.http.get<StudentModel[]>("http://localhost:8080/api/v1/ekool/student/");
  }

  postGradeToApi = (gradeDTO: GradeDTOModel) => {
    return this.http.post(this.apiUrl + "/addGrade", gradeDTO, {headers: this.headers});
  }

  updateGradeInApi = (updateGradeDTO: UpdateGradeDTOModel): Observable<any> => {
    return this.http.put((this.apiUrl + "/updateGrade"), updateGradeDTO, {headers: this.headers});
  }

  deleteGradeInApi = (id: number): Observable<any> => {
    return this.http.delete((this.apiUrl + `/deleteGrade/${id}`), {headers: this.headers});
  }
}
