package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Staff;
import com.mvms.movie_management_system.repository.StaffRepository;
import com.mvms.movie_management_system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StaffController{
    @Autowired
    private StaffService staffService;

    @GetMapping("/staff")
    public List<Staff> fetchStaff(){
        System.out.println("StaffController - fetchStaff()");
        return staffRepository.findAll();
    }
    @PostMapping("/staff")
    public Staff createStaff(@RequestBody Staff staff){
        System.out.println("StaffController - createStaff()");
        if (staff.getStaffId() != null && staffService.getStaffbyID(staff.getStaffId())){
            throw new IllegalArgumentException("Staff[id = " + staff.getStaffId() + "] has already existed.")
        }
    }
    @PutMapping("/staff/{id}")
    public Staff updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        System.out.println("StaffController - updateStaff()");
        Optional<Staff> dbStaff = staffService.getStaffById(id);
        if (dbStaff.isEmpty()) {
            throw new IllegalArgumentException("Staff[id = " + id + "] does not exist.");
        }
        else {
            return staffService.updateStaff(id, staff);
        }
    }

    @DeleteMapping("/staff/{id}")
    public String deleteStaff(@PathVariable Long id) {
        System.out.println("StaffController - deleteStaff()");
        Optional<Staff> dbStaff = staffService.getStaffById(id);
        if (dbStaff.isEmpty()) {
            throw new IllegalArgumentException("Staff[id = " + id + "] does not exist.");
        } else {
            staffService.deleteStaff(id);
            return "Staff[id = " + id + "] was deleted!";
        }
    }
}