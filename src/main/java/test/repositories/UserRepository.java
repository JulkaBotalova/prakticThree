package test.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import test.entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findById(Long id);
    List<User> findAll();

    @Query("SELECT COUNT(entity)>0 FROM User entity WHERE entity.id = :#{#_entity.id}")
    boolean isIdExists(@Param("_entity") User _entity);

   @Modifying
    @Transactional
    @Query("DELETE FROM User entity WHERE entity.id = :#{#_id}")
    void delete(@Param("_id") Optional<User> _id);
}
