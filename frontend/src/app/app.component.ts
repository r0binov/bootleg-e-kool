import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GradeService} from "./service/grade.service";
import {GradeModel} from "./model/grade.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
@Injectable({providedIn: "root"})
export class AppComponent implements OnInit{
  title = 'frontend';
  constructor(private gradeService: GradeService) {
  }
  grade: GradeModel[] = [];

  ngOnInit(): void {
    this.gradeService.getLogsFromApi().subscribe((data) => {
      this.grade = data;
      console.log(data)
    })
  }
}
