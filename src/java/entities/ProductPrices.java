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
@Table(name = "product_prices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductPrices.findAll", query = "SELECT p FROM ProductPrices p"),
    @NamedQuery(name = "ProductPrices.findById", query = "SELECT p FROM ProductPrices p WHERE p.productPricesPK.id = :id"),
    @NamedQuery(name = "ProductPrices.findByProductId", query = "SELECT p FROM ProductPrices p WHERE p.productPricesPK.productId = :productId"),
    @NamedQuery(name = "ProductPrices.findBySalePrice", query = "SELECT p FROM ProductPrices p WHERE p.salePrice = :salePrice")})
public class ProductPrices implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductPricesPK productPricesPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Products products;

    public ProductPrices() {
    }

    public ProductPrices(ProductPricesPK productPricesPK) {
        this.productPricesPK = productPricesPK;
    }

    public ProductPrices(ProductPricesPK productPricesPK, BigDecimal salePrice) {
        this.productPricesPK = productPricesPK;
        this.salePrice = salePrice;
    }

    public ProductPrices(int id, int productId) {
        this.productPricesPK = new ProductPricesPK(id, productId);
    }

    public ProductPricesPK getProductPricesPK() {
        return productPricesPK;
    }

    public void setProductPricesPK(ProductPricesPK productPricesPK) {
        this.productPricesPK = productPricesPK;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
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
        hash += (productPricesPK != null ? productPricesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductPrices)) {
            return false;
        }
        ProductPrices other = (ProductPrices) object;
        if ((this.productPricesPK == null && other.productPricesPK != null) || (this.productPricesPK != null && !this.productPricesPK.equals(other.productPricesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProductPrices[ productPricesPK=" + productPricesPK + " ]";
    }
    
}
