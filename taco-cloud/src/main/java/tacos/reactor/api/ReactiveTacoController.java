package tacos.reactor.api;

// import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.reactor.domain.ReactiveTaco;
import tacos.reactor.repository.ReactiveTacoRepository;

@RestController
@RequestMapping(path = "/api/reactor/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class ReactiveTacoController {
    private ReactiveTacoRepository tacoRepo;

    @Autowired
    public ReactiveTacoController(ReactiveTacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/{id}")
    public Mono<ReactiveTaco> tacoById(@PathVariable("id") Long id) {
        return tacoRepo.findById(id);
    }

    @GetMapping(params = "recent")
    public Flux<ReactiveTaco> recentTacos() {
        return tacoRepo.findAll().take(12);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ReactiveTaco> postTaco(@RequestBody Mono<ReactiveTaco> tacoMono) {
        return tacoRepo.saveAll(tacoMono).next();
    }

    // @PostMapping(consumes = "application/json")
    // @ResponseStatus(HttpStatus.CREATED)
    // public Mono<ReactiveTaco> postTaco(@RequestBody
    // Mono<ReactiveTaco> tacoMono) {
    // return tacoMono.flatMap(tacoRepo::save);
    // }

    // @GetMapping(params = "recent")
    // public Observable<ReactiveTaco> recentTacos() {
    // return tacoService.getRecentTacos();
    // }

    // @GetMapping("/{id}")
    // public Single<ReactiveTaco> tacoById(@PathVariable("id") Long id) {
    // return tacoService.lookupTaco(id);
    // }

}
