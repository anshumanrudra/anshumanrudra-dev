/**
 * 
 */
package test.barclays.pricing.rule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.barclays.pricing.entity.RetailerProductLink;
import test.barclays.pricing.exception.PricingException;

/**
 * @class ChosenPriceRule
 *
 */
public class ChosenPriceRule implements IPricingRule {

	/* (non-Javadoc)
	 * @see test.barclays.pricing.rule.IPricingRule#apply(java.util.List)
	 */
	@Override
	public <T extends RetailerProductLink> List<T> apply(List<T> t) throws PricingException {
		// fetch the chosen price from the list of products based on the rule -
		// when multiple prices occur frequently, the least amongst them is chosen
		
		Map<String, T> productPriceMap = new HashMap<>();
		for (int i = 0; i < t.size(); i++) {
			T rp = t.get(i);
			BigDecimal price = null;
			if (productPriceMap.containsKey(rp.getProduct().getCode())) {
				price = productPriceMap.get(rp.getProduct().getCode()).getPrice();
			}
			// check if the previous product was same to evaluate average
			BigDecimal avg = null;
			if (i > 0) {
				T prevrp = t.get(i-1);
				if (prevrp.getProduct().getCode().equals(rp.getProduct().getCode())) {
					// calculate the 50% of the average price
					avg = prevrp.getPrice().add(rp.getPrice()).divide(new BigDecimal(2)).multiply(new BigDecimal(0.5)).setScale(PricingRuleFactory.PRICE_SCALE, PricingRuleFactory.PRICE_ROUNDING_MODE); 
				}
			}
			// put the chosen price for the product
			if (price == null || price.compareTo(rp.getPrice()) > 0) {
				// Prices less than 50% of average price are treated as promotion and not considered.
				// Prices more than 50% of average price are treated as data errors and not considered.
				if (avg == null || !(rp.getPrice().compareTo(avg) <= 0)) {
					productPriceMap.put(rp.getProduct().getCode(), rp);
				}
			}
		}
		t.clear();
		productPriceMap.values().forEach(pp->t.add(pp));
		return t;
	}

	@Override
	public <T extends RetailerProductLink> List<T> before(List<T> t) throws PricingException {
		// TODO Auto-generated method stub
		return t;
	}

	@Override
	public <T extends RetailerProductLink> List<T> after(List<T> t) throws PricingException {
		// TODO Auto-generated method stub
		return t;
	}

}
