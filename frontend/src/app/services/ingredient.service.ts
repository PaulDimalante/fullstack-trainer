import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Ingredient } from '../models/ingredient';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  
  constructor(private httpClient: HttpClient) { }

  findById(id: number): Observable<Ingredient> {
    return this.httpClient.get<Ingredient>(environment.ingredientsURL + '/findById/' + id);
  }

  findAll(): Observable<Array<Ingredient>> {
    return this.httpClient.get<Array<Ingredient>>(environment.ingredientsURL + '/findAll');
  }

  save(ingredient: Ingredient): Observable<any> {
    return this.httpClient.post(environment.ingredientsURL + '/save', ingredient);    
  }

}
