package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entityAudit.OrderAudit;

import java.util.List;
import java.util.Optional;

public interface OrderAuditRepository extends PagingAndSortingRepository<OrderAudit, Integer> {
    Optional<OrderAudit> findById(Long id);
    List<OrderAudit> findAll();

}
