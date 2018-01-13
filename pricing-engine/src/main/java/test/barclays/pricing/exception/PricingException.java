package test.barclays.pricing.exception;

public class PricingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PricingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PricingException(String arg0) {
		super(arg0);
	}

}
