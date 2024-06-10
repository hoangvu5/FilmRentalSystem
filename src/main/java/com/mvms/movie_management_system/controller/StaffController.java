package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Staff;
import com.mvms.movie_management_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StaffController{
    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/staff")
    public List<Staff> fetchStaff(){
        System.out.println("StaffController - fetchStaff()");
        return staffRepository.findAll();
    }
    @PostMapping("/staff")
    public Staff createStaff(@RequestBody Staff staff){
        System.out.println("StaffController - createStaff()");
        if (staff.getStaffId() != null && staffRepository.findById(staff.getStaffId()).isPresent()){
            throw new IllegalArgumentException("Staff[id = " + staff.getStaffId() + "] has already existed.");
        }
        return staffRepository.save(staff);
    }

    @PutMapping("/staff/{id}")
    public Staff updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        System.out.println("StaffController - updateStaff()");
        Optional<Staff> dbStaff = staffRepository.findById(id);
        if (dbStaff.isEmpty()) {
            throw new IllegalArgumentException("Staff[id = " + id + "] does not exist.");
        }
        else {
            return staffRepository.save(staff);
        }
    }

    @DeleteMapping("/staff/{id}")
    public String deleteStaff(@PathVariable Long id) {
        System.out.println("StaffController - deleteStaff()");
        Optional<Staff> dbStaff = staffRepository.findById(id);
        if (dbStaff.isEmpty()) {
            throw new IllegalArgumentException("Staff[id = " + id + "] does not exist.");
        } else {
            staffRepository.deleteById(id);
            return "Staff[id = " + id + "] was deleted!";
        }
    }
}