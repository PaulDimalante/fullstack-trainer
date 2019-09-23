import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IngredientListComponent } from './componets/ingredient-list/ingredient-list.component';
import { IngredientDetailComponent } from './componets/ingredient-detail/ingredient-detail.component';
import { IngredientService } from './services/ingredient.service';
//import { Ingredient } from './models/ingredient';

@NgModule({
  declarations: [
    AppComponent,
    IngredientListComponent,
    IngredientDetailComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
    ],
  providers: [
              {provide: IngredientService, useClass: IngredientService}
            ],
  bootstrap: [AppComponent]
})
export class AppModule { }
