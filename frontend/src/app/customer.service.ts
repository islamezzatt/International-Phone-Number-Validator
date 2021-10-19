import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnvironmentConfig, ENV_CONFIG } from './environment-config';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl!: string;
  constructor(@Inject(ENV_CONFIG) config: EnvironmentConfig, private http: HttpClient) {
    this.baseUrl = `${config.environment.customerBaseUrl}`;
   }

  getCustomers(params: any): Observable<any>{
    return this.http.get<any>(this.baseUrl + "/customer/contact", {params});
  }
}
