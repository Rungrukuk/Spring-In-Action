package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.domain.User;
import tacos.repository.UserRepository;

import org.springframework.boot.ApplicationRunner;

@Configuration
public class DataLoaderConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public ApplicationRunner dataLoader(
                        UserRepository repo, PasswordEncoder encoder) {
                return args -> {
                        repo.save(
                                        new User("habuma", encoder.encode("password"), "ROLE_USER"));
                        repo.save(
                                        new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
                };
        }
}
