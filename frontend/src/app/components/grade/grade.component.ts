import {Component, OnInit} from '@angular/core';
import {GradeService} from "../../service/grade.service";
import {GradeModel} from "../../model/grade.model";
import {GradeDTOModel} from "../../model/gradeDTO.model";
import {UpdateGradeDTOModel} from "../../model/updateGradeDTO.model";

@Component({
  selector: 'app-grade',
  templateUrl: './grade.component.html',
  styleUrls: ['./grade.component.css']
})
export class GradeComponent implements OnInit {
  constructor(private gradeService: GradeService) {
  }

  grade: GradeModel[] = [];
  newGrade: GradeDTOModel = new GradeDTOModel(0, 0, 0)
  updatedGrade: UpdateGradeDTOModel = new UpdateGradeDTOModel(0, 0);

  ngOnInit(): void {
    this.gradeService.getLogsFromApi().subscribe((data) => {
      this.grade = data;
      console.log(data)
    })
  }

  addGrade(): void {
    this.gradeService.postGradeToApi(this.newGrade).subscribe({
      next: (response: any) => {
        this.newGrade = response;
      },
      error: (error) => {
        console.error('Error occured in adding grade:', error);
      }
    });
  }

  updateGrade(): void {
    this.gradeService.editGradeInApi(this.updatedGrade).subscribe({
      next: (response: any) => {
        this.updatedGrade = response;
      },
      error: (error) => {
        console.error('Error occured in adding grade:', error);
      }
    });
  }

  deleteGrade(id: number): void {
    this.gradeService.deleteGradeInApi(id).subscribe({
      next: () => {
        this.grade = this.grade.filter((grade) => grade.id !== id)
      }
      ,
      error:(error) =>
    {
      console.error('Error deleting log:', error);
    }
  })
  }
}
