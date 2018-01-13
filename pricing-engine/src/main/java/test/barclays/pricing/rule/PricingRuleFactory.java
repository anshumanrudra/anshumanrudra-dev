package test.barclays.pricing.rule;

import java.math.RoundingMode;

import test.barclays.pricing.exception.PricingException;
import test.barclays.pricing.logger.Logger;

public class PricingRuleFactory {

	private static final PricingRuleFactory INSTANCE = new PricingRuleFactory();
	private static final String RULE_BASE_PKG = "test.barclays.pricing.rule.";
	protected static final Integer PRICE_SCALE = 1;
	protected static final RoundingMode PRICE_ROUNDING_MODE = RoundingMode.FLOOR;
	
	private PricingRuleFactory() {
		//do nothing for singleton
	}
	
	@Override
	public PricingRuleFactory clone() {
		return INSTANCE;
	}
	
	public static PricingRuleFactory getInstance() {
		return INSTANCE;
	}
	
	public <PR extends IPricingRule> PR getRule(String ruleKey) throws PricingException {
		String msg = "No rule definition found for the provided rule key: "+ ruleKey;
		try{
			@SuppressWarnings("unchecked")
			PR pr = (PR)Class.forName(RULE_BASE_PKG+ruleKey).newInstance();
			return pr;
		}catch(ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
			Logger.getLogger().error(msg);
			throw new PricingException(msg, ex);
		}
	}
}
