package spring.jpa.demo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ngoc on 26/03/2018
 * @project spring-data-jpa
 */
@Entity
@Table(name = "Customers")
@Getter
@Setter
public class Customer
{
    @Id
    @Column(name = "CustomerId")
    private String customerId;

    @Column(name = "CustomerName")
    @NonNull
    private String customerName;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Fax")
    private String fax;

    @Column(name = "Email")
    private String email;

    @Column(name = "Status")
    private String stutus;

    @Column(name = "Description")
    private String description;
}
