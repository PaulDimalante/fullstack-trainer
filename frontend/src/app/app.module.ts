import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IngredientListComponent } from './componets/ingredient-list/ingredient-list.component';
import { IngredientDetailComponent } from './componets/ingredient-detail/ingredient-detail.component';
import { IngredientService } from './services/ingredient.service';

@NgModule({
  declarations: [
    AppComponent,
    IngredientListComponent,
    IngredientDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
    ],
  providers: [
              {provide: IngredientService, useClass: IngredientService}
            ],
  bootstrap: [AppComponent]
})
export class AppModule { }
