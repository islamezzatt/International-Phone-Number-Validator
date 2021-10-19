import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './customer';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = "http://localhost:8090/customer/contact"
  constructor(private http: HttpClient) { }

  getCustomers(params: any): Observable<any>{
    return this.http.get<any>(this.baseUrl, {params});
  }
}
