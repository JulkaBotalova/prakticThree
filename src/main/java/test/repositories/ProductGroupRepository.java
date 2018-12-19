package test.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import test.entity.ProductGroup;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductGroupRepository extends PagingAndSortingRepository<ProductGroup, Integer>{
    Optional<ProductGroup> findById(Long id);
    List<ProductGroup> findAll();

    @Query("SELECT COUNT(entity)>0 FROM ProductGroup entity WHERE entity.id = :#{#_entity.id}")
    boolean isIdExists(@Param("_entity") ProductGroup _entity);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProductGroup entity WHERE entity.id = :#{#_id}")
    void delete(@Param("_id") Optional<ProductGroup> _id);

}

