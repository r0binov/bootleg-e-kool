import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { StudentModel } from '../model/student.model'; 

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

  getStudents = () => {
    return this.http.get<StudentModel[]>(this.apiUrl + "/");
  }

  addStudent(student: StudentModel) {
    return this.http.post(this.apiUrl + '/addStudent', student, { headers: this.headers });
  }

  updateStudent(student: StudentModel) {
    return this.http.put(this.apiUrl + '/updateStudent', student, { headers: this.headers });
  }

  deleteStudent = (studentId: number) => {
    return this.http.delete((this.apiUrl + `/deleteStudent/${studentId}`), {headers: this.headers});
  }
}