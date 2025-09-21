import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LabsecService {
  private apiUrl = 'http://localhost:8080/labsec';

  constructor(private http: HttpClient) {}

  getLabsec(n: number): Observable<string> {
    return this.http.get(`${this.apiUrl}/${n}`, { responseType: 'text' });
  }
}
