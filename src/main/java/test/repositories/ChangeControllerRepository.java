package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.ChangeController;

import java.util.List;
import java.util.Optional;

public interface ChangeControllerRepository extends PagingAndSortingRepository<ChangeController, Integer> {
    Optional<ChangeController> findById(Integer id);
    List<ChangeController> findAll();
}