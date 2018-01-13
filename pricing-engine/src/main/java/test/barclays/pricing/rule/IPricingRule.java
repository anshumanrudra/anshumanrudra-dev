package test.barclays.pricing.rule;

import java.util.List;

import test.barclays.pricing.entity.RetailerProductLink;
import test.barclays.pricing.exception.PricingException;

public interface IPricingRule {

	/**
	 * before applying rules
	 * @param t
	 * @return
	 * @throws PricingException
	 */
	<T extends RetailerProductLink> List<T> before(List<T> t) throws PricingException;
	
	/**
	 * apply the current rule on the input provided
	 * @param t
	 * @return
	 * @throws PricingException
	 */
	<T extends RetailerProductLink> List<T> apply(List<T> t) throws PricingException;
	
	/**
	 * after applying rules
	 * @param t
	 * @return
	 * @throws PricingException
	 */
	<T extends RetailerProductLink> List<T> after(List<T> t) throws PricingException;
}
