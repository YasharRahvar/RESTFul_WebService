/**
 * Project: rest_project
 * File: EmployeeIDValidator.java
 * Date: Jul 24, 2017
 * Time: 12:25:48 PM
 */

package rest_peoject.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yashar Rahvar
 *
 */
public class EmployeeIDValidator {
	/**
	 * 
	 * @param input
	 * @param pattern
	 * @return
	 */
	public static boolean isValidInput(String input, String pattern) {
		Pattern patt = Pattern.compile(pattern);
		Matcher match = patt.matcher(input);
		return match.matches();
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static String correctNameFormat(String name) {
		String correctedName = name.substring(0, 1).toUpperCase() + name.substring(1);
		return correctedName;
	}
}
