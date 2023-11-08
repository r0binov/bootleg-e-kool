import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GradeService } from './service/grade.service';
import {HttpClientModule} from "@angular/common/http";
import { GradeComponent } from './components/grade/grade.component';
import {FormsModule} from "@angular/forms";
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import {StudentService} from "./service/student.service";
import {StudentComponent} from "./components/student/student.component";
import {SubjectService} from "./service/subject.service";
import {SubjectComponent} from "./components/subject/subject.component";

@NgModule({
  declarations: [
    AppComponent,
    GradeComponent,
    StudentComponent,
    SubjectComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [GradeService, StudentService, SubjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
