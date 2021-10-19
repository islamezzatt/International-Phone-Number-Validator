import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CustomerComponent } from './customer/customer/customer.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule } from '@angular/forms';
import { HttpModule } from './http/http.module';
import { environment } from 'src/environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgxPaginationModule,
    FormsModule,
    HttpModule.forRoot({ environment})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
