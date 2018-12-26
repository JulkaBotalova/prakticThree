package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entityAudit.OrderPosAudit;

import java.util.List;
import java.util.Optional;

public interface OrderPosAuditRepository extends PagingAndSortingRepository<OrderPosAudit, Integer> {
    Optional<OrderPosAudit> findById(Long id);
    List<OrderPosAudit> findAll();
}