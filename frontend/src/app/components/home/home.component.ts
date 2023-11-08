import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private router: Router) {
  }

  navigateTo(destination: string) {
    if (destination === 'subject') {
      this.router.navigate(['/subject']);
    } else if (destination === 'student') {
      this.router.navigate(['/student']);
    } else if (destination === 'grade') {
      this.router.navigate(['/grade']);
    }
  }
}
