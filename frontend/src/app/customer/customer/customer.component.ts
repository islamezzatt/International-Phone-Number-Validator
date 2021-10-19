import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/customer';
import { CustomerService } from 'src/app/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customers!: Customer[];
  currentCustomer!: Customer;
  currentIndex = -1;
  country = '';
  state = '';

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3,6,9];
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.retreiveCustomers();
  }

  getRequestParams(searchState: string, searchCountry: string, page: number, pageSize: number): any {
    let params: any = {};

    if(searchState){
      params[`state`] = searchState;
    }

    if(searchCountry){
      params[`country`] = searchCountry;
    }

    if (page){
      params[`page`] = page - 1;
    }

    if (pageSize){
      params[`pageSize`] = pageSize;
    }

    return params;
  }

  retreiveCustomers(): void {
    const params = this.getRequestParams(this.state, this.country, this.page, this.pageSize);

    this.customerService.getCustomers(params)
      .subscribe(response => {
        const {customers, totalItems} = response;
        this.customers = customers;
        this.count = totalItems;
        console.log(params);
        console.log(response);
      },
      error => {
        console.log(error);
      }
      )
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.retreiveCustomers();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retreiveCustomers();
  }

  searchCountry(): void{
    this.page = 1;
    this.retreiveCustomers();
  }

  searchState(): void{
    this.page = 1;
    this.retreiveCustomers();
  }
}
