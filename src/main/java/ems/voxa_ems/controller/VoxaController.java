package ems.voxa_ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ems.voxa_ems.entity.EmployeesDetails;
import ems.voxa_ems.service.EmployeeService;


@Controller
public class VoxaController {

  @Autowired
  EmployeeService service;

    @GetMapping("/")
    public String home() {
        return "home.html";
    }
    @GetMapping("/employees")
    public String manage()
    {
        return "manage.html";
    }
    @GetMapping("/add")
     public String add()
     {
        return "add.html";
     } 
    
     @PostMapping("/add")
     public String add(@ModelAttribute EmployeesDetails emp,ModelMap map) {
         
         service.add(emp,map);
         return "add.html";
     }
     @GetMapping("/get")
     public String get()
     {
        return "findbyid.html";
     }
     @PostMapping("/get")
     public String get(@RequestParam Long id,ModelMap map)
     {

        Optional<EmployeesDetails> empolyeesDetails=service.findbyrecord(id,map);
         if(empolyeesDetails.isPresent()) {
       map.put("employee", empolyeesDetails.get());
    } 
    else {
        map.put("error", "Employee not found with ID: " + id);
    }
         
        return "findbyid";

     }
     @GetMapping("/view")
     public String view(ModelMap map)
     {
        List<EmployeesDetails> empolyeesDetails=service.findallrecords();
        map.put("employeesdetails", empolyeesDetails);
        return "view.html";
     }
     @GetMapping("/edit")
     public String update(ModelMap map) {
        List<EmployeesDetails> empolyeesDetails=service.findallrecords();
        map.put("employeesdetails", empolyeesDetails);
         return "update.html";
     }
     @GetMapping("/edit/{id}")
     public String edit(@PathVariable Long id,ModelMap map){
        service.edit(id,map);
        return "edit";
     }
     @PostMapping("update")
     public String update(@ModelAttribute EmployeesDetails empolyeesDetails,ModelMap map)
     {
            service.update(empolyeesDetails,map);
            
            return update(map);
     }
     @GetMapping("/delete")
     public String delete(ModelMap map)
     {
       List<EmployeesDetails> empolyeesDetails=service.findallrecords();
        map.put("employeesdetails", empolyeesDetails);
         return "delete.html";
     }
     @GetMapping("/delete/{id}")
     public String delete(@PathVariable Long id,ModelMap map)
     {
      
        service.delete(id, map);
        return delete(map);
        
     }

     

}
