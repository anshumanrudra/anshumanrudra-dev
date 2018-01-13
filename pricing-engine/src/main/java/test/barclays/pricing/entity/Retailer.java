/**
 * 
 */
package test.barclays.pricing.entity;

import java.io.Serializable;

/**
 * @class Retailer
 *
 */
public class Retailer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
