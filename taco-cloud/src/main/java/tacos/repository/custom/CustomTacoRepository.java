package tacos.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tacos.domain.Taco;

public interface CustomTacoRepository {
    Page<Taco> fetchNextPage(Long lastId, String lastValue, Pageable pageable);
}
