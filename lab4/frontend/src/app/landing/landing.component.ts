import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { IProduct } from '../../models/product';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: [
    '../../assets/css/bootstrap.css',
    '../../assets/css/style.css'
  ],
})
@Injectable()
export class LandingComponent implements OnInit {
  product?: IProduct[];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<IProduct[]>(environment.baseUrl)
    .subscribe((data: IProduct[]) => {
      this.product = data;
    });
  }
}
