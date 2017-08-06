/**
 * Project: rest_project
 * File: EmployeeFacadeInterface.java
 * Date: Jul 19, 2017
 * Time: 8:01:56 PM
 */

package rest_project.jpa.data;

import java.util.List;

import javax.ejb.Local;

import rest_project.jpa.entity.Employee;

/**
 * @author Yashar Rahvar
 *
 */
@Local
public interface EmployeeFacadeInterface {
	/**
	 * 
	 * @return returns list of employees from database.
	 */
	List<Employee> getEmployees();

	/**
	 * 
	 * @param id employee id.
	 * @return returns the employee from database. 
	 */
	Employee findEmployee(String id);

	/**
	 * 
	 * @param id Employee id.
	 * @return returns true if employee exist else returns false.
	 */
	boolean deleteEmployee(String id);

	/**
	 * 
	 * @param ID employee id.
	 * @param firstName employee firstName.
	 * @param lastName employee lastName.
	 * @param dob employee date of birth.
	 */
	void addEmployee(Employee employee);

}
