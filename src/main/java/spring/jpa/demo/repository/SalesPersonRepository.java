package spring.jpa.demo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.jpa.demo.model.SalesPerson;

import java.util.List;
import java.util.Optional;

/**
 * @author ngoc on 24/03/2018
 * @project spring-data-jpa
 */
public interface SalesPersonRepository extends CommonRepository<SalesPerson>
{

//    @Query("select s from SalesPerson s where s.salesPersonName like %?1 and s.itemLimit > ?2")
//    List<SalesPerson> search(String name, Double itemLimit);

//    List<SalesPerson> findBySalesPersonNameContainsAndItemLimitGreaterThan(String name, Double itemLimit);

//    @Query("select s from SalesPerson  s where s.salesPersonName like %:salesPersonName% and s.itemLimit > :itemLimit")
//    List<SalesPerson> findBySalesPersonNameAndItemLimit(@Param("salesPersonName") String salesPersonName, @Param("itemLimit") Double itemLimit);

//    @Query("select s from #{#entityName} s")
//    List<SalesPerson> listAllSalesPerson();

//    Optional<SalesPerson> findByFirstNameOrLastName(String firstName, String lastName);
}
