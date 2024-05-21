package com.salesmanager.core.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RECENT_ORDERS")
public class OrderCherry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	
	@Column(name = "PRICE")
	private String price;
	
	
	@Column(name = "PRODUCT_DISCOUNT")
	private String productDiscount;
	
	
	@Column(name = "PRODUCT_DESCRIPITION")
	private String productDescripition;
	
	@Column(name = "IMAGE")
	private String image;
	
	@Column(name = "ORDER_ID")
	private String orderId;
	
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	@Column(name = "IMAGE_URL")
	private String imageUrl;
	
	
	
	public int getQuantity() {
		return quantity;
	}

	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getProductDiscount() {
		return productDiscount;
	}


	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}


	public String getProductDescripition() {
		return productDescripition;
	}


	public void setProductDescripition(String productDescripition) {
		this.productDescripition = productDescripition;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	

}
