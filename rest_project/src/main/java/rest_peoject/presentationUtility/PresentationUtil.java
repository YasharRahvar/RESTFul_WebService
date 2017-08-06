/**
 * Project: rest_project
 * File: PresentationUtil.java
 * Date: Jul 24, 2017
 * Time: 12:24:58 PM
 */

package rest_peoject.presentationUtility;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author Yashar Rahvar
 *
 */
public class PresentationUtil {
	private static Logger log = Logger.getLogger(PresentationUtil.class);
	private static final String BUNDLE_NAME = "employees-presentation";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * 
	 * @param key
	 *            The key for properties file messages.
	 * @return This returns properties file message.
	 */
	public static String getString(String key) {
		try {
			if (System.getenv(key) != null) {
				return System.getenv(key);
			}
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			log.error("Unable to find key [" + key + "] in " + BUNDLE_NAME);
			return '!' + key + '!';
		}
	}
}
