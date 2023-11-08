import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GradeService } from './service/grade.service';
import {HttpClientModule} from "@angular/common/http";
import { GradeComponent } from './components/grade/grade.component';
import {FormsModule} from "@angular/forms";
import { SubjectComponent } from './components/subject/subject.component';
import { HomeComponent } from './components/home/home.component';
import {SubjectService} from "./service/subject.service";
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    GradeComponent,
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
  providers: [GradeService, SubjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
