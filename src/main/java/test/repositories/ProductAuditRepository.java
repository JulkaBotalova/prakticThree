package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entityAudit.ProductAudit;

import java.util.List;
import java.util.Optional;

public interface ProductAuditRepository extends PagingAndSortingRepository<ProductAudit, Integer> {
    Optional<ProductAudit> findById(Long id);
    List<ProductAudit> findAll();

}

