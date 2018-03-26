package spring.jpa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spring.jpa.demo.model.SalesPerson;
import spring.jpa.demo.repository.SalesPersonRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ngoc on 24/03/2018
 * @project spring-data-jpa
 */
@Service
public class SalesPersonService extends CommonService<SalesPerson>
{
    @Autowired
    private SalesPersonRepository salesPersonRepository;

//    public Optional<SalesPerson> getByFirstNameOrLastName(String firstName, String lastName)
//    {
//        return salesPersonRepository.findByFirstNameOrLastName(firstName, lastName);
//    }

    @Override
    public List<SalesPerson> search(SalesPerson filter)
    {
        List<SalesPerson> salesPeople = salesPersonRepository.findAll(new Specification()
        {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if (filter.getFirstName() != null) {
                    predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + filter.getFirstName() + "%"));
                }
                if (filter.getLastName() != null) {
                    predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + filter.getLastName() + "%"));
                }
                if (filter.getStatus() != null && filter.getStatus().compareToIgnoreCase("select") != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        });
        return salesPeople;
    }
}