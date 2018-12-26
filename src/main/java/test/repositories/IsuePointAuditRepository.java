package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entityAudit.IsuePointAudit;

import java.util.List;
import java.util.Optional;

public interface IsuePointAuditRepository extends PagingAndSortingRepository<IsuePointAudit, Integer> {
    Optional<IsuePointAudit> findById(Integer id);
    List<IsuePointAudit> findAll();

}

