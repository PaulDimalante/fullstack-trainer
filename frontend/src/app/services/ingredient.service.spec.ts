import { TestBed, inject } from '@angular/core/testing';

import { IngredientService } from './ingredient.service';
import { Ingredient } from '../models/ingredient';
import {
  HttpTestingController,
  HttpClientTestingModule
} from "@angular/common/http/testing";
import { environment } from "../../environments/environment";

describe('IngredientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [IngredientService]
    });
  });

  it('should be created', inject([IngredientService], (service: IngredientService) => {
    expect(service).toBeTruthy();
  }));

  it('should return all ingredients by calling http service', 
    inject([IngredientService, HttpTestingController], (service: IngredientService, httpMock: HttpTestingController) => {

      const mockResponse = [
        new Ingredient(1, "hot sauce"),
        new Ingredient(2, "soy sauce")
        
      ]
      
      service.findAll().subscribe((response) => {
        expect(response).toEqual(mockResponse);
      });
      
      const mockReq = httpMock.expectOne(environment.ingredientsURL + '/findAll');

      mockReq.flush(mockResponse);

      httpMock.verify();
  }));

  it('should return single ingredient by calling http service', 
    inject([IngredientService, HttpTestingController], (service: IngredientService, httpMock: HttpTestingController) => {

      const mockResponse = new Ingredient(1, "hot sauce");
      
      service.findById(1).subscribe((response) => {
        expect(response).toEqual(mockResponse);
      });
      
      const mockReq = httpMock.expectOne(environment.ingredientsURL + '/findById/1');

      expect(mockReq.request.method).toEqual("GET");

      mockReq.flush(mockResponse);

      httpMock.verify();
  }));

  it('should post supplied ingredient via http', 
  inject([IngredientService, HttpTestingController], (service: IngredientService, httpMock: HttpTestingController) => {
    const mockArg = new Ingredient(1, "hot sauce");
      
    service.save(mockArg).subscribe();
    
    const mockReq = httpMock.expectOne(environment.ingredientsURL + '/save');

    expect(mockReq.request.method).toEqual("POST");
    expect(mockReq.request.body).toEqual(mockArg);

    mockReq.flush({});

    httpMock.verify();
  }));

});
