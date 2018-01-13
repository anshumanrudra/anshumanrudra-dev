/**
 * 
 */
package test.barclays.pricing.rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

import test.barclays.pricing.entity.RetailerProductLink;
import test.barclays.pricing.exception.PricingException;

/**
 * @class SupplyDemandPriceRule
 *
 */
public class SupplyDemandPriceRule implements IPricingRule {

	/* (non-Javadoc)
	 * @see test.barclays.pricing.rule.IPricingRule#apply(java.util.List)
	 */
	@Override
	public <T extends RetailerProductLink> List<T> apply(List<T> rpls) throws PricingException {
		//If Supply is High and Demand is High, Product is sold at same price as chosen price.
		//If Supply is Low and Demand is Low, Product is sold at 10 % more than chosen price.
		//If Supply is Low and Demand is High, Product is sold at 5 % more than chosen price.
		//If Supply is High and Demand is Low, Product is sold at 5 % less than chosen price.
		Consumer<T> con = new Consumer<T>() {

			@Override
			public void accept(T t) {
				if ("H".equals(t.getSupplyFlag())) {
					if ("H".equals(t.getDemandFlag())) {
						// no change to the chosen price of the product
					} else {
						t.setPrice(t.getPrice().subtract(t.getPrice().multiply(new BigDecimal(0.05))).setScale(PricingRuleFactory.PRICE_SCALE, PricingRuleFactory.PRICE_ROUNDING_MODE));
					}
				} else {
					if ("H".equals(t.getDemandFlag())) {
						t.setPrice(t.getPrice().add(t.getPrice().multiply(new BigDecimal(0.05))).setScale(PricingRuleFactory.PRICE_SCALE, PricingRuleFactory.PRICE_ROUNDING_MODE));
					} else {
						t.setPrice(t.getPrice().add(t.getPrice().multiply(new BigDecimal(0.10))).setScale(PricingRuleFactory.PRICE_SCALE, PricingRuleFactory.PRICE_ROUNDING_MODE));
					}
				}
				
			}
		};
		rpls.forEach(con);
		return rpls;
	}

	@Override
	public <T extends RetailerProductLink> List<T> before(List<T> t) throws PricingException {
		return t;
	}

	@Override
	public <T extends RetailerProductLink> List<T> after(List<T> t) throws PricingException {
		// TODO Auto-generated method stub
		return t;
	}

}
