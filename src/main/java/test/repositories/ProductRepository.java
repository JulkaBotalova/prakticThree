package test.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import test.entity.Product;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
    Optional<Product> findById(Long id);
    List<Product> findAll();

    @Query("SELECT COUNT(entity)>0 FROM Product entity WHERE entity.id = :#{#_entity.id}")
    boolean isIdExists(@Param("_entity") Product _entity);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product entity WHERE entity.id = :#{#_id}")
    void delete(@Param("_id") Optional<Product> _id);


}

