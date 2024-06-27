package tacos.web.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.Taco;
import tacos.repository.TacoRepository;
import tacos.utils.PageInfo;

@Slf4j
@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
    private TacoRepository tacoRepo;

    @Autowired
    public TacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTacos(
            @RequestParam(value = "page_info", required = false) String pageInfoStr,
            @RequestParam(value = "limit", defaultValue = "4") int limit) {

        PageInfo pageInfo = null;

        if (pageInfoStr != null) {
            pageInfo = PageInfo.decodePageInfo(pageInfoStr);
        }

        PageRequest pageRequest = PageRequest.of(0, limit, Sort.by("createdAt").ascending());
        log.info("OFFSET: " + pageRequest.getOffset());
        log.info("PAGE SIZE: " + pageRequest.getPageSize());

        Page<Taco> page;

        if (pageInfo != null && "next".equals(pageInfo.getDirection())) {
            page = tacoRepo.fetchNextPage(pageInfo.getLastId(), pageInfo.getLastValue(), pageRequest);
        } else {
            page = tacoRepo.findAll(pageRequest);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("tacos", page.getContent());

        HttpHeaders headers = new HttpHeaders();
        if (page.hasNext()) {
            PageInfo nextPageInfo = new PageInfo();
            nextPageInfo.setLastId(page.getContent().get(page.getContent().size() - 1).getId());
            nextPageInfo.setLastValue(page.getContent().get(page.getContent().size() - 1).getCreatedAt().toString());
            nextPageInfo.setDirection("next");

            String nextPageToken = PageInfo.encodePageInfo(nextPageInfo);
            headers.add(HttpHeaders.LINK,
                    String.format("</api/tacos?limit=%d&page_info=%s>; rel=\"next\"", limit, nextPageToken));
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return ResponseEntity.ok(optTaco.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @PutMapping(path = "/{tacoId}", consumes = "application/json")
    public ResponseEntity<?> putTaco(@PathVariable("tacoId") Long tacoId, @RequestBody Taco taco) {
        if (taco.getName() == null || taco.getName().isBlank()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Name cannot be blank\"}");
        } else if (taco.getIngredients() == null || taco.getIngredients().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Ingredients must contain at least 1 ingredient\"}");
        }
        taco.setId(tacoId);
        Taco updatedTaco = tacoRepo.save(taco);
        return ResponseEntity.ok(updatedTaco);
    }

    @PatchMapping(path = "/{tacoId}", consumes = "application/json")
    public ResponseEntity<?> patchTaco(@PathVariable("tacoId") Long tacoId, @RequestBody Taco updatedTaco) {
        // Find the existing Taco from the repository
        Optional<Taco> optionalTaco = tacoRepo.findById(tacoId);

        // If Taco not found, return 404 Not Found
        if (!optionalTaco.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("\"error\": \"Couldn't find requested taco\"");
        }

        Taco existingTaco = optionalTaco.get();

        // Update the name if it's not null or blank
        if (updatedTaco.getName() != null && !updatedTaco.getName().isBlank()) {
            existingTaco.setName(updatedTaco.getName());
        }

        // Update the ingredients if provided
        if (updatedTaco.getIngredients() != null && !updatedTaco.getIngredients().isEmpty()) {
            existingTaco.setIngredients(updatedTaco.getIngredients());
        }

        // Save the updated Taco
        Taco savedTaco = tacoRepo.save(existingTaco);

        // Return the updated Taco with a 200 OK response
        return ResponseEntity.ok(savedTaco);
    }

    @DeleteMapping("/{tacoId}")
    public ResponseEntity<?> deleteTaco(@PathVariable("tacoId") Long tacoId) {
        // Check if the Taco with the specified ID exists
        if (!tacoRepo.existsById(tacoId)) {
            // Return 404 Not Found if Taco does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("\"error\": \"Couldn't find requested taco\"");
        }

        // Delete the Taco by its ID
        tacoRepo.deleteById(tacoId);

        // Return 204 No Content to indicate successful deletion
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
    }

}
