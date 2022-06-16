import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { IProduct } from '../../models/product';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: [
    '../../assets/css/bootstrap.css',
    '../../assets/css/style.css'
  ],
})
@Injectable()
export class FormComponent {
  form = this.formBuilder.group<IProduct>({
    name: '',
    description: '',
    imageUrl: '',
    price: 0,
  });

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient
  ) {}

  onSubmit() {
    console.log(this.form.value);

    this.http.post(environment.baseUrl, this.form.value)
      .subscribe((response) => {
        alert("New Product Added");
      });
  }
}
