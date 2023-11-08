import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubjectModel} from "../model/subject.model";

@Injectable()
export class SubjectService {
  private apiUrl = "http://localhost:8080/api/v1/ekool/subject"
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
  });
  constructor(private http: HttpClient) {
  }


  getSubjectFromApi = (): Observable<SubjectModel[]> => {
    return this.http.get<SubjectModel[]>(this.apiUrl + "/");
  }

  postSubjectToApi = (subject: SubjectModel) => {
    return this.http.post(this.apiUrl + "/addSubject", subject, {headers: this.headers});
  }

  updateSubjectInApi = (subject: SubjectModel) => {
    return this.http.put((this.apiUrl + "/updateSubject"), subject, {headers: this.headers});
  }

  deleteSubjectInApi = (id: number): Observable<any> => {
    return this.http.delete((this.apiUrl + `/deleteSubject/${id}`), {headers: this.headers});
  }
}
