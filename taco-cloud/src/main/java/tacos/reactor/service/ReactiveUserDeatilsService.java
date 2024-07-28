package tacos.reactor.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;
import tacos.reactor.repository.ReactiveUserRepository;

public class ReactiveUserDeatilsService {
    @Bean
    public ReactiveUserDetailsService userDetailsService(
            ReactiveUserRepository userRepo) {
        return new ReactiveUserDetailsService() {
            @Override
            public Mono<UserDetails> findByUsername(String username) {
                return userRepo.findByUsername(username)
                        .map(user -> {
                            return user.toUserDetails();
                        });
            }
        };
    }
}
