package tacos.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tacos.domain.Taco;
import tacos.repository.custom.CustomTacoRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomTacoRepositoryImpl implements CustomTacoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Taco> fetchNextPage(Long lastId, String lastValue, Pageable pageable) {
        String jpql = "SELECT t FROM Taco t WHERE t.id > :lastId AND t.createdAt > :lastValue ORDER BY t.createdAt ASC";
        TypedQuery<Taco> query = entityManager.createQuery(jpql, Taco.class);
        query.setParameter("lastId", lastId);

        Date lastDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            lastDate = dateFormat.parse(lastValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        query.setParameter("lastValue", lastDate);

        query.setMaxResults(pageable.getPageSize());

        List<Taco> tacos = query.getResultList();
        long total = getTotalCount(lastId, lastValue);

        return new PageImpl<>(tacos, pageable, total);
    }

    private long getTotalCount(Long lastId, String lastValue) {
        String jpql = "SELECT COUNT(t) FROM Taco t WHERE t.id > :lastId AND t.createdAt > :lastValue";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("lastId", lastId);

        Date lastDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            lastDate = dateFormat.parse(lastValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        query.setParameter("lastValue", lastDate);

        return query.getSingleResult();
    }
}
