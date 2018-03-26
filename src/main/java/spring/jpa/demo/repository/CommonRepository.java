package spring.jpa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ngoc on 26/03/2018
 * @project spring-data-jpa
 */
@NoRepositoryBean
public interface CommonRepository<T> extends JpaRepository<T, String>, JpaSpecificationExecutor
{
//    @Query("select t from #{#entityName} t")
//    Page<T> findAll(Pageable pageable);

//    @Query("select e from #{#entityName} e where e.attribute = ?1")
//    T findByAttribute(String attribute, Class<T> type);
}
