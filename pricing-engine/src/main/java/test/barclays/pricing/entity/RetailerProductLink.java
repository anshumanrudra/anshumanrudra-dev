package test.barclays.pricing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class RetailerProductLink implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Retailer retailer;
	private Product product;
	private BigDecimal price;
	private String demandFlag;
	private String supplyFlag;
	
	public String getDemandFlag() {
		return demandFlag;
	}
	public void setDemandFlag(String demandFlag) {
		this.demandFlag = demandFlag;
	}
	public String getSupplyFlag() {
		return supplyFlag;
	}
	public void setSupplyFlag(String supplyFlag) {
		this.supplyFlag = supplyFlag;
	}
	public Retailer getRetailer() {
		return retailer;
	}
	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
