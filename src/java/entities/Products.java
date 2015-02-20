/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaume
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id"),
    @NamedQuery(name = "Products.findByBarcode", query = "SELECT p FROM Products p WHERE p.barcode = :barcode"),
    @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name"),
    @NamedQuery(name = "Products.findByVat", query = "SELECT p FROM Products p WHERE p.vat = :vat"),
    @NamedQuery(name = "Products.findBySalePrice", query = "SELECT p FROM Products p WHERE p.salePrice = :salePrice")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "barcode")
    private String barcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "vat")
    private BigDecimal vat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductProvider> productProviderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Collection<OrderLines> orderLinesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Collection<ProductPrices> productPricesCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductsCategories categoryId;

    public Products() {
    }

    public Products(Integer id) {
        this.id = id;
    }

    public Products(Integer id, String barcode, String name, BigDecimal vat, BigDecimal salePrice) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.vat = vat;
        this.salePrice = salePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    @XmlTransient
    public Collection<ProductProvider> getProductProviderCollection() {
        return productProviderCollection;
    }

    public void setProductProviderCollection(Collection<ProductProvider> productProviderCollection) {
        this.productProviderCollection = productProviderCollection;
    }

    @XmlTransient
    public Collection<OrderLines> getOrderLinesCollection() {
        return orderLinesCollection;
    }

    public void setOrderLinesCollection(Collection<OrderLines> orderLinesCollection) {
        this.orderLinesCollection = orderLinesCollection;
    }

    @XmlTransient
    public Collection<ProductPrices> getProductPricesCollection() {
        return productPricesCollection;
    }

    public void setProductPricesCollection(Collection<ProductPrices> productPricesCollection) {
        this.productPricesCollection = productPricesCollection;
    }

    public ProductsCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ProductsCategories categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Products[ id=" + id + " ]";
    }
    
}
