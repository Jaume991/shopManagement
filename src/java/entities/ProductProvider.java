/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "product_provider")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductProvider.findAll", query = "SELECT p FROM ProductProvider p"),
    @NamedQuery(name = "ProductProvider.findById", query = "SELECT p FROM ProductProvider p WHERE p.id = :id"),
    @NamedQuery(name = "ProductProvider.findByDiscount", query = "SELECT p FROM ProductProvider p WHERE p.discount = :discount"),
    @NamedQuery(name = "ProductProvider.findByCostPrice", query = "SELECT p FROM ProductProvider p WHERE p.costPrice = :costPrice")})
public class ProductProvider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private BigDecimal discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost_price")
    private BigDecimal costPrice;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productId;
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Providers providerId;

    public ProductProvider() {
    }

    public ProductProvider(Integer id) {
        this.id = id;
    }

    public ProductProvider(Integer id, BigDecimal discount, BigDecimal costPrice) {
        this.id = id;
        this.discount = discount;
        this.costPrice = costPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public Providers getProviderId() {
        return providerId;
    }

    public void setProviderId(Providers providerId) {
        this.providerId = providerId;
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
        if (!(object instanceof ProductProvider)) {
            return false;
        }
        ProductProvider other = (ProductProvider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProductProvider[ id=" + id + " ]";
    }
    
}
