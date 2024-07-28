package tacos.reactor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
// import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
// import org.springframework.util.StreamUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.domain.Taco;
import tacos.reactor.api.ReactiveTacoController;
import tacos.reactor.repository.ReactiveTacoRepository;

public class TacoControllerTest {

    private final long TESTID = 12345;

    @Test
    public void shouldReturnRecentTacos() {
        Taco[] tacos = {
                testTaco(1L), testTaco(2L),
                testTaco(3L), testTaco(4L),
                testTaco(5L), testTaco(6L),
                testTaco(7L), testTaco(8L),
                testTaco(9L), testTaco(10L),
                testTaco(11L), testTaco(12L),
                testTaco(13L), testTaco(14L),
                testTaco(15L), testTaco(16L) };
        Flux<Taco> tacoFlux = Flux.just(tacos);
        ReactiveTacoRepository tacoRepo = Mockito.mock(ReactiveTacoRepository.class);
        when(tacoRepo.findAll()).thenReturn(tacoFlux);
        WebTestClient testClient = WebTestClient.bindToController(
                new ReactiveTacoController(tacoRepo))
                .build();
        testClient.get().uri("/api/tacos?recent")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
                .jsonPath("$[0].name").isEqualTo("Taco 1")
                .jsonPath("$[1].id").isEqualTo(tacos[1].getId().toString())
                .jsonPath("$[1].name").isEqualTo("Taco 2")
                .jsonPath("$[11].id").isEqualTo(tacos[11].getId().toString())
                .jsonPath("$[11].name").isEqualTo("Taco 12")
                .jsonPath("$[12]").doesNotExist();
        // ClassPathResource recentsResource = new
        // ClassPathResource("/tacos/recent-tacos.json");
        // try {
        // String recentsJson = StreamUtils.copyToString(
        // recentsResource.getInputStream(), Charset.defaultCharset());
        // testClient.get().uri("/api/tacos?recent")
        // .accept(MediaType.APPLICATION_JSON)
        // .exchange()
        // .expectStatus().isOk()
        // .expectBody()
        // .json(recentsJson);

        // } catch (Exception e) {
        // System.out.println(e);
        // }
    }

    private Taco testTaco(Long number) {
        Taco taco = new Taco();
        taco.setId(number != null ? number : TESTID);
        taco.setName("Taco " + number);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(
                new Ingredient("INGA", "Ingredient A", Type.WRAP));
        ingredients.add(
                new Ingredient("INGB", "Ingredient B", Type.PROTEIN));
        taco.setIngredients(ingredients);
        return taco;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldSaveATaco() {
        ReactiveTacoRepository tacoRepo = Mockito.mock(
                ReactiveTacoRepository.class);
        WebTestClient testClient = WebTestClient.bindToController(
                new ReactiveTacoController(tacoRepo)).build();
        Mono<Taco> unsavedTacoMono = Mono.just(testTaco(1L));
        Taco savedTaco = testTaco(1L);
        Flux<Taco> savedTacoMono = Flux.just(savedTaco);
        when(tacoRepo.saveAll(any(Mono.class))).thenReturn(savedTacoMono);
        testClient.post()
                .uri("/api/tacos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unsavedTacoMono, Taco.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Taco.class)
                .isEqualTo(savedTaco);
    }
}
