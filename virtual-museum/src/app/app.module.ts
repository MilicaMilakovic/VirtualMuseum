import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NbThemeModule, NbLayoutModule } from '@nebular/theme';
import { NbEvaIconsModule } from '@nebular/eva-icons';
import { AppNebularModule } from './app-nebular/app-nebular.module';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { FooterComponent } from './components/footer/footer.component';
import { NewsComponent } from './components/news/news.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { MuseumsComponent } from './components/museums/museums.component';
import { ExhibitionsComponent } from './components/exhibitions/exhibitions.component';
import { MuseumCardComponent } from './components/museums/museum-card/museum-card.component';
import { HttpClientModule } from '@angular/common/http';
import { MuseumViewComponent } from './components/museums/museum-view/museum-view.component';
import { RssItemComponent } from './components/news/rss-item/rss-item.component';
import { TourCardComponent } from './components/exhibitions/tour-card/tour-card.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { NbToastrModule } from '@nebular/theme';
import { NbWindowModule } from '@nebular/theme';
import { NbTabsetModule } from '@nebular/theme';
import { NbStepperModule } from '@nebular/theme';
import { NbDatepickerModule } from '@nebular/theme';
import { NbTimepickerModule } from '@nebular/theme';
import { NbCalendarKitModule } from '@nebular/theme';
import { ProfileComponent } from './components/profile/profile.component';
import { TicketComponent } from './components/profile/ticket/ticket.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { NavComponent } from './components/admin/nav/nav.component';
import { MuseumsManagementComponent } from './components/admin/museums-management/museums-management.component';
import { UsersManagementComponent } from './components/admin/users-management/users-management.component';
import { MuseumFormComponent } from './components/admin/museums-management/museum-form/museum-form.component';
import { TourFormComponent } from './components/admin/museums-management/tour-form/tour-form.component';
import { TourComponent } from './components/tour/tour.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomePageComponent,
    RegistrationComponent,
    LoginComponent,
    FooterComponent,
    NewsComponent,
    NavigationComponent,
    MuseumsComponent,
    ExhibitionsComponent,
    MuseumCardComponent,
    MuseumViewComponent,
    RssItemComponent,
    TourCardComponent,
    CheckoutComponent,
    ProfileComponent,
    TicketComponent,
    DashboardComponent,
    NavComponent,
    MuseumsManagementComponent,
    UsersManagementComponent,
    MuseumFormComponent,
    TourFormComponent,
    TourComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NbThemeModule.forRoot({ name: 'default' }),
    NbLayoutModule,
    NbEvaIconsModule,
    AppNebularModule,
    FormsModule,
    HttpClientModule,
    NbDatepickerModule ,
    NbCalendarKitModule ,
    NbStepperModule ,
    NbTabsetModule, 
    NbToastrModule.forRoot(),
    NbWindowModule.forRoot(),
    NbDatepickerModule.forRoot(),
    NbTimepickerModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
