package spring.jpa.demo.model;

import lombok.Getter;
import lombok.Setter;
import spring.jpa.demo.converter.DoubleConverter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ngoc on 26/03/2018
 * @project spring-data-jpa
 */
@Entity
@Setter
@Getter
@Table(name = "SalesOrderDetails")
public class SalesOrderDetail implements Serializable
{
    @EmbeddedId
    private SalesOrderDetailId id;

    @Column(name = "Qty")
    private int qty;

    @Column(name = "Price")
    @Convert(converter = DoubleConverter.class)
    private Double price;

    @Column(name = "ItemDisc")
    @Convert(converter = DoubleConverter.class)
    private Double itemDisc;

    @Column(name = "TaxAmt")
    @Convert(converter = DoubleConverter.class)
    private Double taxAmt;

    @Column(name = "Amount")
    @Convert(converter = DoubleConverter.class)
    private Double amount;
}
