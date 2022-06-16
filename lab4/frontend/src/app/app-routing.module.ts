import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './form/form.component';
import { LandingComponent } from './landing/landing.component';

const routes: Routes = [
  {
    path: 'form',
    component: FormComponent,
  },
  {
    path: '',
    component: LandingComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
