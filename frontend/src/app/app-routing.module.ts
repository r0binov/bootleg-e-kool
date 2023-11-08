import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GradeComponent} from "./components/grade/grade.component";
import {HomeComponent} from "./components/home/home.component";
import { StudentComponent } from './components/student/student.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'student', component: StudentComponent },
  { path: 'grade', component: GradeComponent },];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
