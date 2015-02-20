/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaume
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
    @NamedQuery(name = "Orders.findByShipDate", query = "SELECT o FROM Orders o WHERE o.shipDate = :shipDate"),
    @NamedQuery(name = "Orders.findByPayDate", query = "SELECT o FROM Orders o WHERE o.payDate = :payDate"),
    @NamedQuery(name = "Orders.findByTotal", query = "SELECT o FROM Orders o WHERE o.total = :total"),
    @NamedQuery(name = "Orders.findByDestiny", query = "SELECT o FROM Orders o WHERE o.destiny = :destiny")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ship_date")
    @Temporal(TemporalType.DATE)
    private Date shipDate;
    @Column(name = "pay_date")
    @Temporal(TemporalType.DATE)
    private Date payDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private float total;
    @Size(max = 45)
    @Column(name = "destiny")
    private String destiny;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<OrderLines> orderLinesCollection;
    @JoinColumn(name = "ship_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ShipType shipTypeId;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customers customerId;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Orders(Integer id, float total) {
        this.id = id;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    @XmlTransient
    public Collection<OrderLines> getOrderLinesCollection() {
        return orderLinesCollection;
    }

    public void setOrderLinesCollection(Collection<OrderLines> orderLinesCollection) {
        this.orderLinesCollection = orderLinesCollection;
    }

    public ShipType getShipTypeId() {
        return shipTypeId;
    }

    public void setShipTypeId(ShipType shipTypeId) {
        this.shipTypeId = shipTypeId;
    }

    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orders[ id=" + id + " ]";
    }
    
}
