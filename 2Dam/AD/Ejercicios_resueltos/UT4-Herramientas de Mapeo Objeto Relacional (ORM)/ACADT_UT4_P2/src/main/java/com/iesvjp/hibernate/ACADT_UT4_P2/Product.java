package com.iesvjp.hibernate.ACADT_UT4_P2;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name = "products")
@NamedQueries({
		@NamedQuery(name = "Product.consultaA", query = "select p from Product p where p.productVendor = :vendedor"),
		@NamedQuery(name = "Product.consultaB", query = "select p from Product p where p.quantityInStock < 100"), 
		@NamedQuery(name = "Product.consultaC", query = "select p from Product p where p.msrp = (select max(pr.msrp) from Product pr)"),
		@NamedQuery(name = "Product.consultaD", query = "select o.product.productName, sum(o.quantityOrdered) as suma from Orderdetail o group by o.product order by suma desc"),
})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String productCode;

	private BigDecimal buyPrice;

	private BigDecimal msrp;

	@Lob
	private String productDescription;

	private String productName;

	private String productScale;

	private String productVendor;

	private short quantityInStock;

	// bi-directional many-to-one association to Orderdetail
	@OneToMany(mappedBy = "product")
	private List<Orderdetail> orderdetails;

	// bi-directional many-to-one association to Productline
	@ManyToOne
	@JoinColumn(name = "productLine")
	private Productline productline;

	public Product() {
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getBuyPrice() {
		return this.buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getMsrp() {
		return this.msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductScale() {
		return this.productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return this.productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public short getQuantityInStock() {
		return this.quantityInStock;
	}

	public void setQuantityInStock(short quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public List<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Orderdetail addOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().add(orderdetail);
		orderdetail.setProduct(this);

		return orderdetail;
	}

	public Orderdetail removeOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().remove(orderdetail);
		orderdetail.setProduct(null);

		return orderdetail;
	}

	public Productline getProductline() {
		return this.productline;
	}

	public void setProductline(Productline productline) {
		this.productline = productline;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", productVendor="
				+ productVendor + "]";
	}

}