package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entityAudit.ProductGroupAudit;

import java.util.List;
import java.util.Optional;

public interface ProductGroupAuditRepository extends PagingAndSortingRepository<ProductGroupAudit, Integer> {
    Optional<ProductGroupAudit> findById(Long id);
    List<ProductGroupAudit> findAll();


}

