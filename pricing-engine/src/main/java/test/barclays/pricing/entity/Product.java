/**
 * 
 */
package test.barclays.pricing.entity;

import java.io.Serializable;

/**
 * @class Product
 *
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
