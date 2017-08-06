/**
 * Project: rest_project
 * File: EmployeeServicesInterface.java
 * Date: Jul 19, 2017
 * Time: 8:08:06 PM
 */

package rest_peoject.services;

import java.util.List;

import javax.ejb.Local;

import rest_project.jpa.entity.ClientResponse;
import rest_project.jpa.entity.Employee;
import rest_project.jpa.entity.ResponseCode;

/**
 * @author Yashar Rahvar
 *
 */
@Local
public interface EmployeeServicesInterface {
	/**
	 * 
	 * @return returns list of employees.
	 */
	List<Employee> getEmployees(); 
	/**
	 * 
	 * @param id Employee id.
	 * @return returns ClientResponse
	 */
	ClientResponse findEmployee(String id);
	
	/**
	 * 
	 * @param id Employee id
	 * @return returns ResposneCode
	 */
	ResponseCode deleteEmployee(String id);
	
	/**
	 * 
	 * @param employee 
	 * @return returns ResponseCode
	 */
	ResponseCode addEmployee(Employee employee);
}
