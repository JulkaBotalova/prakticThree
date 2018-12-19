package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.Test2;

public interface Test2Repository extends PagingAndSortingRepository<Test2, Integer> {


    /*@Query("SELECT T FROM TestEntity T WHERE T.name LIKE CONCAT('%', :text, '%') ")
    List<TestEntity> findByNameNotLikeAnything (@Param("text") String text);*/

}
