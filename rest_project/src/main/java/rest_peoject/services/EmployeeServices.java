/**
 * Project: rest_project
 * File: EmployeeServices.java
 * Date: Jul 19, 2017
 * Time: 8:09:11 PM
 */

package rest_peoject.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import rest_peoject.presentationUtility.PresentationUtil;
import rest_peoject.validators.EmployeeIDValidator;
import rest_project.jpa.data.EmployeeFacadeInterface;
import rest_project.jpa.entity.ClientResponse;
import rest_project.jpa.entity.Employee;
import rest_project.jpa.entity.ResponseCode;

/**
 * @author Yashar Rahvar
 *
 */
@Stateless
public class EmployeeServices implements EmployeeServicesInterface {
	private final static String ID_PATTERN = "[A][0][0-9]{7}";
	private static Logger logger = Logger.getLogger(EmployeeServices.class);

	@EJB
	EmployeeFacadeInterface employeeFacade;

	@Override
	public List<Employee> getEmployees() {
		return employeeFacade.getEmployees();
	}

	@Override
	public ClientResponse findEmployee(String id) {
		ClientResponse clientResponse = new ClientResponse();
		ResponseCode responseCode = new ResponseCode();
		Employee employee = new Employee();

		if (!EmployeeIDValidator.isValidInput(id, ID_PATTERN)) {
			responseCode.setError(PresentationUtil.getString("error.find.employee.invalid.desc"));
			logger.error("Invalid ID Format");
		} else {
			logger.info("Emplyoee id format is correct.");
			employee = employeeFacade.findEmployee(id);
			if (employee == null) {
				logger.info("Emplyoee doesn't exist.");
				responseCode.setDescription(PresentationUtil.getString("error.find.employee.desc"));
				responseCode.setCode(PresentationUtil.getString("error.find.employee.code"));
			} else {
				logger.info("Emplyoee exist.");
				responseCode.setDescription(PresentationUtil.getString("find.employee.success.desc"));
				responseCode.setCode(PresentationUtil.getString("find.employee.success.code"));
			}
		}
		clientResponse.setEmployee(employee);
		clientResponse.setResponseCode(responseCode);
		return clientResponse;
	}

	/**
	 * Delete method is responsible to get employee ID from the client and delete employee if it exist or appropriate
	 * response in case employee did not exist.
	 * 
	 * @param ID
	 *            The user input for employee ID.
	 * @return This returns ResponseCode object.
	 */
	@Override
	public ResponseCode deleteEmployee(String id) {
		ResponseCode responseCode = new ResponseCode();

		if (!EmployeeIDValidator.isValidInput(id, ID_PATTERN)) {
			logger.error("Invalid ID Format.");
			responseCode.setError(PresentationUtil.getString("error.find.employee.invalid.desc"));
		} else {
			logger.info("Emplyoee format is correct.");
			if (employeeFacade.deleteEmployee(id)) {
				logger.info("Emplyoee exist.");
				responseCode.setDescription(PresentationUtil.getString("remove.employee.success.desc"));
				responseCode.setCode(PresentationUtil.getString("remove.employee.success.code"));
			} else {
				logger.error("Emplyoee doesn't exist.");
				responseCode.setDescription(PresentationUtil.getString("error.remove.employee.desc"));
				responseCode.setCode(PresentationUtil.getString("error.remove.employee.code"));
			}
		}
		return responseCode;
	}

	@Override
	public ResponseCode addEmployee(Employee employee) {
		ResponseCode responseCode = new ResponseCode();
		Employee newEmployee = null;
		if (EmployeeIDValidator.isValidInput(employee.getId(), ID_PATTERN)) {
			logger.info("Valid ID Format.");
			newEmployee = employeeFacade.findEmployee(employee.getId());
		}
		if (newEmployee == null) {
			try {
				logger.info("No duplication founds.");
				employeeFacade.addEmployee(employee);
				responseCode.setDescription(PresentationUtil.getString("add.employee.success.desc"));
				responseCode.setCode(PresentationUtil.getString("add.employee.success.code"));
				logger.info("Employee added successfully.");
			} catch (Exception e1) {
				logger.error("Error adding employee.");
				responseCode.setError(e1.getMessage());
			}
		} else {
			logger.error("Duplicate employee ID. No employee added.");
			responseCode.setDescription(PresentationUtil.getString("error.add.employee.duplicate.desc"));
			responseCode.setCode(PresentationUtil.getString("error.add.employee.invalid.code"));
		}
		return responseCode;
	}

}
