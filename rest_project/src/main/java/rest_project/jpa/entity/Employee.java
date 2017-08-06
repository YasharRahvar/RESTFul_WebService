/**
 * Project: rest_project
 * File: Employee.java
 * Date: Jul 19, 2017
 * Time: 7:50:40 PM
 */

package rest_project.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Yashar Rahvar
 *
 */
@XmlRootElement
@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Employee.getEmployees", query = "select e from Employee e"),
	@NamedQuery(name = "Employee.findEmployee", query = "select e from Employee e where e.id = :id"),
	@NamedQuery(name = "Employee.deleteEmployee", query = "delete from Employee where id = :id") })
public class Employee {
	@Id
	@Column(unique = true)
	@Pattern(regexp = "[A][0][0-9]{7}")
	@NotNull(message = "Should not be empty.")
	private String id;

	@Column
	@NotNull
	@Size(min = 1, message = "Should not be empty.")
	private String firstName;

	@NotNull
	@Size(min = 1, message = "Should not be empty.")
	private String lastName;

	@Column
	@NotNull(message = "Invalid dob format.")
	private Date dob;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + "]";
	}

}
