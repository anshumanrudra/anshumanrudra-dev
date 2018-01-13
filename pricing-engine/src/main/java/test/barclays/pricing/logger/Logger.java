/**
 * 
 */
package test.barclays.pricing.logger;

import java.util.Calendar;

/**
 * @author anshumanrudra
 *
 */
public class Logger {

	
	private final static Logger LOGGER = new Logger();
	
	
	private Logger() {
		// private constructor
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}
	
	@Override
	public Logger clone() {
		return LOGGER;
	}
	
	public void info(String msg) {
		System.out.println(String.format("%s|%s|%s", Calendar.getInstance().getTime(), "INFO", msg));
	}
	
	public void debug(String msg) {
		System.out.println(String.format("%s|%s|%s", Calendar.getInstance().getTime(), "DEBUG", msg));
	}
	
	public void error(String msg) {
		System.out.println(String.format("%s|%s|%s", Calendar.getInstance().getTime(), "ERROR", msg));
	}
}
