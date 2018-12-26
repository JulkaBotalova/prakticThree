package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entityAudit.UserAudit;

import java.util.List;
import java.util.Optional;

public interface UserAuditRepository extends PagingAndSortingRepository<UserAudit, Integer> {
    Optional<UserAudit> findById(Long id);
    List<UserAudit> findAll();


}
