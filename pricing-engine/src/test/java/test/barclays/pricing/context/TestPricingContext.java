package test.barclays.pricing.context;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.barclays.pricing.PricingMain;
import test.barclays.pricing.entity.RetailerProductLink;
import test.barclays.pricing.exception.PricingException;

public class TestPricingContext {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApplyRules1() {
		List<String> lines = Arrays.asList(
		"2",
		"flashdrive H H",
		"ssd L H",
		"5",
		"flashdrive X 1.0",
		"ssd X 10.0",
		"flashdrive Y 0.9",
		"flashdrive Z 1.1",
		"ssd Y 12.5");
		PricingMain pm = new PricingMain();
		PricingContext pc = new PricingContext(Arrays.asList("ChosenPriceRule", "SupplyDemandPriceRule"));
		try {
			List<RetailerProductLink> list = pm.read(lines);
			list = pc.applyRules(list);
			assert(list.get(0).getProduct().getCode().equals("flashdrive"));
			assert(list.get(0).getPrice().compareTo(new BigDecimal("0.9"))==0);
			assert(list.get(1).getProduct().getCode().equals("ssd"));
			assert(list.get(1).getPrice().compareTo(new BigDecimal("10.5"))==0);
		} catch (PricingException e) {
			assert(false);
		}
	}
	
	@Test
	public void testApplyRules2() {
		List<String> lines = Arrays.asList(
		"2",
		"mp3player H H",
		"ssd L L",
		"8",
		"ssd W 11.0",
		"ssd X 12.0",
		"mp3player X 60.0",
		"mp3player Y 20.0",
		"mp3player Z 50.0",
		"ssd V 10.0",
		"ssd Y 11.0",
		"ssd Z 12.0");
		PricingMain pm = new PricingMain();
		PricingContext pc = new PricingContext(Arrays.asList("ChosenPriceRule", "SupplyDemandPriceRule"));
		try {
			List<RetailerProductLink> list = pm.read(lines);
			list = pc.applyRules(list);
			assert(list.get(0).getProduct().getCode().equals("mp3player"));
			assert(list.get(0).getPrice().compareTo(new BigDecimal("50.0"))==0);
			assert(list.get(1).getProduct().getCode().equals("ssd"));
			assert(list.get(1).getPrice().compareTo(new BigDecimal("11.0"))==0);
		} catch (PricingException e) {
			assert(false);
		}
	}

}
