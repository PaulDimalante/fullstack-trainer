import { Component, OnInit } from '@angular/core';
import { IngredientService } from '../../services/ingredient.service';
import { Ingredient } from '../../models/ingredient';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-ingredient-detail',
    templateUrl: './ingredient-detail.component.html',
    styleUrls: ['./ingredient-detail.component.css']
  })
  export class IngredientDetailComponent implements OnInit {
    ingredientFormControl = new FormControl('');
    ingredient: Ingredient;
  
    constructor(private route: ActivatedRoute, private ingredientService: IngredientService) { }
  
    ngOnInit() {
        this.route.paramMap.subscribe((params: ParamMap) => {
            this.findById(+params.get('id'));
          });
    }

    private findById(id: number) {
        debugger;
        this.ingredientService.findById(id).subscribe((data) => {
            debugger;
            this.ingredient = data;
        });
    }
}
