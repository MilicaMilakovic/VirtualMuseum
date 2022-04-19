import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { MuseumsManagementComponent } from './components/admin/museums-management/museums-management.component';
import { UsersManagementComponent } from './components/admin/users-management/users-management.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { ExhibitionsComponent } from './components/exhibitions/exhibitions.component';
import { LoginComponent } from './components/login/login.component';
import { MuseumViewComponent } from './components/museums/museum-view/museum-view.component';
import { MuseumsComponent } from './components/museums/museums.component';
import { NewsComponent } from './components/news/news.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { TourComponent } from './components/tour/tour.component';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';

const routes: Routes = [
  {
    path: '',
    component: WelcomePageComponent,
  },
  {
    path: 'register',
    component: RegistrationComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'news',
    component: NewsComponent,
  },
  {
    path: 'museums',
    component: MuseumsComponent,
  },
  {
    path: 'exhibitions',
    component: ExhibitionsComponent,
  },
  {
    path: 'museums/details',
    component: MuseumViewComponent,
  },
  {
    path: 'checkout/:tourId',
    component: CheckoutComponent,
  },
  {
    path: 'checkout',
    component: WelcomePageComponent,
  },
  {
    path: 'profile',
    component: ProfileComponent,
  },
  {
    path: 'tour',
    component: TourComponent
  },
  {
    path: 'admin/dashboard',
    component: DashboardComponent,
  },
  {
    path: 'admin/museums',
    component: MuseumsManagementComponent
  },
  {
    path: 'admin/users',
    component: UsersManagementComponent
  }
  
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
