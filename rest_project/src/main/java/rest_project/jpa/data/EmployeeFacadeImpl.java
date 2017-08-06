/**
 * Project: rest_project
 * File: EmployeeFacadeImpl.java
 * Date: Jul 19, 2017
 * Time: 8:04:40 PM
 */

package rest_project.jpa.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import rest_project.jpa.entity.Employee;

/**
 * @author Yashar Rahvar
 *
 */
@Stateless
public class EmployeeFacadeImpl implements EmployeeFacadeInterface {
	private static Logger logger = Logger.getLogger(EmployeeFacadeImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Employee> getEmployees() {
		logger.info("Getting list of employees.");
		return entityManager.createNamedQuery("Employee.getEmployees", Employee.class).getResultList();
	}

	@Override
	public Employee findEmployee(String id) {
		logger.info("Finding an employee with ID: " + id);
		try {
			return entityManager.createNamedQuery("Employee.findEmployee", Employee.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public boolean deleteEmployee(String id) {
		boolean checkForDelete = false;
		Employee employee = findEmployee(id);
		if (employee == null) {
			logger.info("No employee found to remove.");
			checkForDelete = false;
		} else {
			entityManager.remove(findEmployee(id));
			logger.info("Employee found and removed.");
			checkForDelete = true;
		}
		return checkForDelete;
	}

	@Override
	public void addEmployee(Employee employee) {
		if (validation(employee)) {
			entityManager.persist(employee);
		}
		entityManager.persist(employee);

	}

	public boolean validation(Employee employee) {
		boolean check = false;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

		if (constraintViolations.size() > 0) {
			check = false;
			Set<String> violationMessages = new HashSet<String>();

			for (ConstraintViolation<Employee> constraintViolation : constraintViolations) {
				violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
			}

			throw new RuntimeException(StringUtils.join(violationMessages, "\n"));
		} else {
			check = true;
		}
		return check;
	}
}
