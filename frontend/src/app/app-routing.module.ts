import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IngredientListComponent } from './componets/ingredient-list/ingredient-list.component';
import { IngredientDetailComponent } from './componets/ingredient-detail/ingredient-detail.component';

const routes: Routes = [
  {path: 'ingredients/findAll', component: IngredientListComponent, pathMatch: 'full'},
  {path: 'ingredients/findById/:id', component: IngredientDetailComponent, pathMatch: 'full'},
  {path: '', redirectTo: 'ingredients/findAll', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
