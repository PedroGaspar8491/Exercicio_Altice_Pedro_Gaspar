import { Component } from '@angular/core';
import { LabsecComponent } from './labsec/labsec.component';

@Component({
  selector: 'app-root',
  imports: [LabsecComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'labsec-angular';
}
