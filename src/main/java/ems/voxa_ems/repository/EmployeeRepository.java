package ems.voxa_ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ems.voxa_ems.entity.EmployeesDetails;

public interface EmployeeRepository extends JpaRepository <EmployeesDetails, Long>{

}
