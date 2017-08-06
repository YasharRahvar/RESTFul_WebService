/**
 * Project: rest_project
 * File: ResponseCode.java
 * Date: Jul 24, 2017
 * Time: 12:11:26 PM
 */

package rest_project.jpa.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Yashar Rahvar
 *
 */
@XmlRootElement
public class ResponseCode {

	private String code;
	private String description;
	private String error;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
