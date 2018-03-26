package spring.jpa.demo.model;

import lombok.Getter;
import lombok.Setter;
import spring.jpa.demo.converter.DoubleConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author ngoc on 26/03/2018
 * @project spring-data-jpa
 */
@Entity
@Table(name = "SalesOrders")
@Getter
@Setter
public class SalesOrder
{
    @Id
    @Column(name = "OrderNo")
    private String orderNo;

    @Column(name = "OrderDate")
    private Date orderDate;

    @Column(name = "OverdueDate")
    private Date overdueDate;

    @ManyToOne
    @JoinColumn(name = "SalesPersonId")
    private SalesPerson salesPerson;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @Column(name = "OrderDisc")
    @Convert(converter = DoubleConverter.class)
    private double orderDisc;

    @Column(name = "TaxAmt")
    @Convert(converter = DoubleConverter.class)
    private double taxAmt;

    @Column(name = "TotalAmt")
    @Convert(converter = DoubleConverter.class)
    private double totalAmt;

    @Column(name = "Payment")
    @Convert(converter = DoubleConverter.class)
    private double payment;

    @Column(name = "Description")
    private String description;

    @Transient
    private List<SalesOrderDetail> items;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesOrder that = (SalesOrder) o;
        return orderNo.equals(that.orderNo);
    }

    @Override
    public int hashCode()
    {
        return orderNo.hashCode();
    }
}
