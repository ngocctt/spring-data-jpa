package spring.jpa.demo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import spring.jpa.demo.converter.DoubleConverter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

/**
 * @author ngoc on 24/03/2018
 * @project spring-data-jpa
 */
@Entity
@Table(name = "SalesPeople")
@Getter
@Setter
public class SalesPerson
{
    @Id
    @Column(name = "SalesPersonId")
    @NonNull
    @NotEmpty
    private String salesPersonId;

    @Column(name = "FirstName")
    @NotEmpty
    @Length(max = 10)
    private String firstName;

    @Column(name = "LastName")
    @NotEmpty
    @Length(max = 30)
    private String lastName;

    @Column(name = "Address")
    private String address;

    @Column(name = "Status")
    private String status;

    @Column(name = "ItemLimit")
    @DecimalMax("100000")
    @DecimalMin("0")
    @Convert(converter = DoubleConverter.class)
    private Double itemLimit;

    @Column(name = "Description")
    private String description;
}
