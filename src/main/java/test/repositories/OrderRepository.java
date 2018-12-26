package test.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import test.entity.Order;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
    Optional<Order> findById(Long id);
    List<Order> findAll();
    @Query("SELECT COUNT(entity)>0 FROM Order entity WHERE entity.id = :#{#_entity.id}")
    boolean isIdExists(@Param("_entity") Order _entity);

    @Modifying
    @Transactional
    @Query("DELETE FROM Order entity WHERE entity.id = :#{#_id}")
    void delete(@Param("_id") Optional<Order> _id);
}
