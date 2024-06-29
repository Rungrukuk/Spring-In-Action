package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.domain.Ingredient;
import tacos.dto.IngredientDTO;
import tacos.repository.IngredientRepository;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http:/ /localhost:8080")
public class IngredientController {
    private IngredientRepository repo;

    @Autowired
    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public IngredientDTO getIngredientById(@PathVariable("id") String ingredientId) {
        Ingredient ingredient = repo.getReferenceById(ingredientId);
        return new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getType());
    }

    @PostMapping
    @PreAuthorize("{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteIngredient(@PathVariable("id") String ingredientId) {
        repo.deleteById(ingredientId);
        return "{}";
    }
}