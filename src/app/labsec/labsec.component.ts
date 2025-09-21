import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LabsecService } from '../labsec.service';

@Component({
  selector: 'app-labsec',
  imports: [CommonModule, FormsModule],
  templateUrl: './labsec.component.html',
  styleUrl: './labsec.component.css',
})
export class LabsecComponent {
  n: number = 0;
  submittedN?: number;
  result?: string;
  error?: string;

  constructor(private labsecService: LabsecService) {}

  ngOnInit() {
    this.labsecService.getLabsec(-1).subscribe({
      next: (res) => console.log('Result:', res),
      error: (err) => console.log('Error object:', err),
    });
  }

  onSubmit() {
    this.submittedN = this.n;
    this.result = undefined;
    this.error = undefined;

    this.labsecService.getLabsec(this.n).subscribe({
      next: (res) => (this.result = this.formatBigInt(res.toString())),
      error: (err) => {
        if (err.status === 0) {
          this.error = 'Cannot connect to the server. Please try again later.';
        } else if (err.status === 400) {
          this.error = 'Invalid input. Please enter a non-negative integer.';
        } else {
          this.error = 'An unexpected error occurred. Please try again.';
        }
      },
    });
  }
  formatBigInt(numStr: string): string {
    if (numStr.length <= 15) return numStr;

    const mantissa = numStr[0] + '.' + numStr.slice(1, 15);
    const exponent = numStr.length - 1;
    return `${mantissa}e${exponent}`;
  }
}
