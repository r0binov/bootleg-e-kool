import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GradeComponent} from "./components/grade/grade.component";
import {SubjectComponent} from "./components/subject/subject.component";
import {HomeComponent} from "./components/home/home.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'subject', component: SubjectComponent },
  { path: 'grade', component: GradeComponent },];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
