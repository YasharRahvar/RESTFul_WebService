/**
 * Project: rest_project
 * File: ClientResponse.java
 * Date: Jul 24, 2017
 * Time: 12:15:18 PM
 */

package rest_project.jpa.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Yashar Rahvar
 *
 */
@XmlRootElement
public class ClientResponse {
	private Employee employee;
	private ResponseCode responseCode;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

}
