/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaume
 */
@Entity
@Table(name = "order_lines")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderLines.findAll", query = "SELECT o FROM OrderLines o"),
    @NamedQuery(name = "OrderLines.findByOrderId", query = "SELECT o FROM OrderLines o WHERE o.orderLinesPK.orderId = :orderId"),
    @NamedQuery(name = "OrderLines.findByProductId", query = "SELECT o FROM OrderLines o WHERE o.orderLinesPK.productId = :productId"),
    @NamedQuery(name = "OrderLines.findByQuantity", query = "SELECT o FROM OrderLines o WHERE o.quantity = :quantity")})
public class OrderLines implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderLinesPK orderLinesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Products products;

    public OrderLines() {
    }

    public OrderLines(OrderLinesPK orderLinesPK) {
        this.orderLinesPK = orderLinesPK;
    }

    public OrderLines(OrderLinesPK orderLinesPK, int quantity) {
        this.orderLinesPK = orderLinesPK;
        this.quantity = quantity;
    }

    public OrderLines(int orderId, int productId) {
        this.orderLinesPK = new OrderLinesPK(orderId, productId);
    }

    public OrderLinesPK getOrderLinesPK() {
        return orderLinesPK;
    }

    public void setOrderLinesPK(OrderLinesPK orderLinesPK) {
        this.orderLinesPK = orderLinesPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderLinesPK != null ? orderLinesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLines)) {
            return false;
        }
        OrderLines other = (OrderLines) object;
        if ((this.orderLinesPK == null && other.orderLinesPK != null) || (this.orderLinesPK != null && !this.orderLinesPK.equals(other.orderLinesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderLines[ orderLinesPK=" + orderLinesPK + " ]";
    }
    
}
