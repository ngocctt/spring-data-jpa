package spring.jpa.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author ngoc on 27/03/2018
 * @project spring-data-jpa
 */
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDetailId implements Serializable
{
    @ManyToOne
    @JoinColumn(name = "OrderNo", insertable = false, updatable = false)
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "ItemId", insertable = false, updatable = false)
    private Item item;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesOrderDetailId that = (SalesOrderDetailId) o;

        if (!salesOrder.equals(that.salesOrder)) return false;
        return item.equals(that.item);
    }

    @Override
    public int hashCode()
    {
        int result = salesOrder.hashCode();
        result = 31 * result + item.hashCode();
        return result;
    }
}
