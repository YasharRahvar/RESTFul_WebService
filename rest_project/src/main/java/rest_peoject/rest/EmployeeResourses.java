/**
 * Project: rest_project
 * File: EmployeeResourses.java
 * Date: Jul 19, 2017
 * Time: 8:11:20 PM
 */

package rest_peoject.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import rest_peoject.services.EmployeeServicesInterface;
import rest_project.jpa.entity.ClientResponse;
import rest_project.jpa.entity.Employee;
import rest_project.jpa.entity.ResponseCode;

/**
 * @author Yashar Rahvar
 *
 */
@Stateless
@Path("/employee")
public class EmployeeResourses {
	private static Logger logger = Logger.getLogger(EmployeeResourses.class);

	@EJB
	EmployeeServicesInterface employeeServices;

	/**
	 * 
	 * @return returns list of employees.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getEmployees() {
		logger.info("Getting employees.");
		return employeeServices.getEmployees();
	}

	/**
	 * 
	 * @param id
	 *            employee id.
	 * @return returns clientResponse object( Employee and ResponseCode).
	 */
	@Path("/find/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ClientResponse findEmployee(@PathParam("id") String id) {
		logger.info("Finding an employee.");
		return employeeServices.findEmployee(id);
	}

	/**
	 * 
	 * @param id
	 *            employee id.
	 * @return returns responseCode object.
	 */
	@Path("/remove/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ResponseCode deleteEmployee(@PathParam("id") String id) {
		logger.info("deleteing an employee");
		return employeeServices.deleteEmployee(id);
	}

	/**
	 * 
	 * @param employee
	 *            employee object.
	 * @return returns ResponseCode.
	 */
	@PUT
	@Path("/add")
	@Produces(MediaType.APPLICATION_XML)
	public ResponseCode addEmployee(Employee employee) {
		logger.info("Adding an employee");
		return employeeServices.addEmployee(employee);

	}

}
