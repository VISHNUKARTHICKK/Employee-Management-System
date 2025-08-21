package ems.voxa_ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import ems.voxa_ems.entity.EmployeesDetails;
import ems.voxa_ems.repository.EmployeeRepository;

@Component
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
    

    public void add(EmployeesDetails emp, ModelMap map) {
        repository.save(emp);
        map.put("pass","Employee Added Succsesfully");
    }

 
    public List<EmployeesDetails> findallrecords() {
       return repository.findAll();
    }


    public void edit(Long id, ModelMap map) {
       EmployeesDetails empolyee=repository.findById(id).get();
       map.put("employee", empolyee);
    }

    public Optional<EmployeesDetails> findbyrecord(Long id,ModelMap map) {
        
        return repository.findById(id);
       
    }


    public void delete(Long id, ModelMap map) {
        repository.deleteById(id);
          map.put("pass", id +" details deleted successfully");

    }


    public void update(EmployeesDetails employeesDetails, ModelMap map) {
        
        repository.save(employeesDetails);
        map.put("pass", employeesDetails.name +" details updated successfully");
    }


    

}
