package test.barclays.pricing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.barclays.pricing.context.PricingContext;
import test.barclays.pricing.entity.Product;
import test.barclays.pricing.entity.Retailer;
import test.barclays.pricing.entity.RetailerProductLink;
import test.barclays.pricing.exception.PricingException;
import test.barclays.pricing.logger.Logger;

/**
 * @class PricingMain
 *
 */
public class PricingMain {
	
    public static void main(String[] args ) {
        List<RetailerProductLink> rplCompList = null;
        String line;
        List<String> lines = new ArrayList<>();
        PricingMain pm = new PricingMain();
    	try {
    		// read input
	    	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); 
	        while ((line = stdin.readLine()) != null && line.length()!= 0) {
	        	lines.add(line);
	        }
    		rplCompList = pm.read(lines);
	        // Apply rules
	        PricingContext pc = new PricingContext(Arrays.asList("ChosenPriceRule", "SupplyDemandPriceRule"));
	        rplCompList = pc.applyRules(rplCompList);
	        // print results
	        rplCompList.forEach(rpl->Logger.getLogger().info(rpl.getProduct().getCode()+" "+rpl.getPrice()));
    	} catch(Exception ex) {
    		Logger.getLogger().error("Error while processing "+ ex.getMessage());
    		ex.printStackTrace();
    	}
    }
    
    public List<RetailerProductLink> read(List<String> lines) throws PricingException {
    	Logger.getLogger().info("start reading input");
        int i = 0;
        int count = 0;
        boolean productSupplyDemandLoaded = false;
        Map<String, RetailerProductLink> productsupplyDemandMap = new HashMap<>();
        List<RetailerProductLink> rplCompList = new ArrayList<>();
    	try {
    		for (String line: lines) {
	        	if (i == 0) {
	        		count = Integer.parseInt(line.trim());
	        		i++;
	        		continue;
	        	}
        		String[] tokens = line.split("[ ]");
        		Product p = new Product();
        		Retailer r = new Retailer();
        		RetailerProductLink rpl = new RetailerProductLink();
        		rpl.setProduct(p);
        		rpl.setRetailer(r);
        		p.setCode(tokens[0]);
        		if (!productSupplyDemandLoaded) {
	        		rpl.setSupplyFlag(tokens[1]);
	        		rpl.setDemandFlag(tokens[2]);
	        		productsupplyDemandMap.put(p.getCode(), rpl);
        		} else {
        			r.setName(tokens[1]);
        			rpl.setPrice(new BigDecimal(tokens[2]));
        			rpl.setDemandFlag(productsupplyDemandMap.get(rpl.getProduct().getCode()).getDemandFlag());
    	        	rpl.setSupplyFlag(productsupplyDemandMap.get(rpl.getProduct().getCode()).getSupplyFlag());
        			rplCompList.add(rpl);
        		}
		        if (i == count) {
	        		i = 0;
	        		productSupplyDemandLoaded = productSupplyDemandLoaded || productsupplyDemandMap.size() == count;
	        		continue;
	        	}
		        i++;
	        }
    	} catch(Exception ex) {
    		Logger.getLogger().error("Error while processing "+ ex.getMessage());
    		throw new PricingException("Error while reading input ", ex);
    	}
    	Logger.getLogger().info("complete reading input");
    	return rplCompList;
    }
}
