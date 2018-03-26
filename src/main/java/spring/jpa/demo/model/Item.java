package spring.jpa.demo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import spring.jpa.demo.converter.DoubleConverter;

import javax.persistence.*;

/**
 * @author ngoc on 26/03/2018
 * @project spring-data-jpa
 */
@Entity
@Table(name = "Items")
@Getter
@Setter
public class Item
{
    @Id
    @Column(name = "ItemId")
    private String itemId;

    @Column(name = "ItemName")
    @NonNull
    private String itemName;

    @Column(name = "Qty")
    private int qty;

    @Column(name = "QtyStock")
    private int qtyStock;

    @Column(name = "Price")
    @Convert(converter = DoubleConverter.class)
    private double price;

    @Column(name = "Unit")
    private String unit;

    @Column(name = "ItemDisc")
    @Convert(converter = DoubleConverter.class)
    private double itemDisc;

    @Column(name = "TaxAmt")
    @Convert(converter = DoubleConverter.class)
    private double taxAmt;

    @Column(name = "Amount")
    @Convert(converter = DoubleConverter.class)
    private double amount;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return itemId.equals(that.itemId);
    }

    @Override
    public int hashCode()
    {
        return itemId.hashCode();
    }
}
