import { Component, OnInit } from '@angular/core';
import { IngredientService } from '../../services/ingredient.service';
import { Ingredient } from '../../models/ingredient';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
    selector: 'app-ingredient-detail',
    templateUrl: './ingredient-detail.component.html',
    styleUrls: ['./ingredient-detail.component.css']
  })
  export class IngredientDetailComponent implements OnInit {
    ingredient: Ingredient = new Ingredient(null, null);
    id: number;
  
    constructor(private route: ActivatedRoute, private ingredientService: IngredientService) { }
  
    ngOnInit() {
        this.route.paramMap.subscribe((params: ParamMap) => {
            this.id = +params.get('id');
            if(this.id) {
                this.findById(this.id);
            } else {
                this.add();
            }
          });
    }

    private findById(id: number) {
        this.ingredientService.findById(id).subscribe(data => {
           this.ingredient = data;
        });
    }

    public save(ingredient: Ingredient) {
        this.ingredientService.save(ingredient).subscribe(data => {
            this.ingredient = data;
        });
    }

    public add() {
        this.ingredient = new Ingredient(null, '');
    }

    public delete(ingredient: Ingredient) {
        this.ingredientService.delete(ingredient).subscribe(data => {
            this.ingredient = data;
        });
    }
}
