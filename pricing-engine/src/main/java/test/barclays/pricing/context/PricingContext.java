/**
 * 
 */
package test.barclays.pricing.context;

import java.util.List;

import test.barclays.pricing.entity.RetailerProductLink;
import test.barclays.pricing.exception.PricingException;
import test.barclays.pricing.logger.Logger;
import test.barclays.pricing.rule.IPricingRule;
import test.barclays.pricing.rule.PricingRuleFactory;

/**
 * @author anshumanrudra
 *
 */
public class PricingContext {

	private List<String> rules;
	
	private PricingContext() {
		//private default constructor, so that rules gets initialized 
	}
	
	public PricingContext(List<String> rules) {
		this.rules = rules;
	}
	
	public <T extends RetailerProductLink> List<T> applyRules(List<T> list) throws PricingException {
		try {
			// Apply rules
			for(String rule: rules) {
				Logger.getLogger().info("intializing rule from factory for key: "+ rule);
		        IPricingRule pr = PricingRuleFactory.getInstance().getRule(rule);
		        list = pr.before(list);
		        list = pr.apply(list);
		        list = pr.after(list);
		        Logger.getLogger().info("complete applying rule from factory for key: "+ rule);
			}
			return list;
		} catch(Exception ex) {
			Logger.getLogger().error("Error while processing rules: "+ ex.getMessage());
			throw new PricingException("Error while processing rules ", ex);
		}
	}
}
