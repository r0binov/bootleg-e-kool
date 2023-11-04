import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GradeService } from './service/grade.service';
import {HttpClientModule} from "@angular/common/http";
import { GradeComponent } from './components/grade/grade.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    GradeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [GradeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
