package tacos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootTest
@EnableR2dbcRepositories(basePackages = "tacos.reactor.repository")
class TacoCloudApplicationTests {

	@Test
	void contextLoads() {
	}

}
