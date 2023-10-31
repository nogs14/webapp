package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {
	
//	@Autowired
//	private CustomProperties customProperties;
	//	private String baseApiUrl = customProperties.getApiUrl();
	@Value("${com.openclassrooms.webapp.apiUrl}")
	private String baseApiUrl;
	

	private RestTemplate restTemplate = new RestTemplate();
	
	/***
	 * Create an employee
	 * @return an Employee
	 */
	
	public Employee createEmployee(Employee e) {
		String createEmployeeUrl = baseApiUrl + "/employee";
		
		HttpEntity<Employee> request = new HttpEntity<Employee>(e);
		ResponseEntity<Employee> response = restTemplate.exchange(
				createEmployeeUrl,
				HttpMethod.POST,
				request,
				Employee.class);
		log.debug("Create Employee call" + response.getStatusCode().toString());
		return response.getBody();
	}
	
	/**
	 * Update an employee - using the PUT HTTP Method.
	 * @param e Existing employee to update
	 * @return an Employee
	 */
	public Employee updateEmployee(Employee e) {
		String updateEmployeeUrl = baseApiUrl + "/employee/" + e.getId();
		HttpEntity<Employee> request = new HttpEntity<Employee>(e);
		ResponseEntity<Employee> response = restTemplate.exchange(
				updateEmployeeUrl,
				HttpMethod.PUT,
				request,
				Employee.class);
		log.debug("Update Employee call" + response.getStatusCode().toString());
		return response.getBody();
	}
	
	/***
	* Get all employees
	* @return An iterable of all employees
	*/
	
	public Iterable<Employee> getEmployees(){
		String getEmployeesUrl = baseApiUrl + "/employees";
		
		ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
				getEmployeesUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Employee>>(){});
		log.debug("Get Employees call" + response.getStatusCode().toString());
		return response.getBody();
	}
	
	/***
	 * Get an employee by id
	 * @param id the id of the employee
	 * @return the employee which matches the id
	 */
	public Employee getEmployee(int id) {
		String getEmployeeUrl = baseApiUrl + "/employee/" + id;
		ResponseEntity<Employee> response = restTemplate.exchange(
				getEmployeeUrl,
				HttpMethod.GET,
				null,
				Employee.class);
		log.debug("Get Employee call" + response.getStatusCode().toString());
		return response.getBody();
	}
	
	/***
	* Delete an employee by id
	* @param id the id of the employee
	*/
	public void deleteEmployee(int id) {
		String deleteEmployeeUrl = baseApiUrl +  "/employee/" + id;
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteEmployeeUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		log.debug("Delete Employee call" + response.getStatusCode().toString());
	}
}
