import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { UserModule } from './user/user.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegistrationModule } from './registration/registration.module';
import { SearchModule } from './search/search.module';
import { MyclassesModule } from './myclasses/myclasses.module';
import { FinanceModule } from './finance/finance.module';
import { AdminModule } from './admin/admin.module';
//import { EventEmitterService } from './event-emitter.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    UserModule,
    RegistrationModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SearchModule,
    MyclassesModule,
    FinanceModule,
    AdminModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
