package com.mvms.movie_management_system.service;

import com.mvms.movie_management_system.entity.Staff;
import com.mvms.movie_management_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class StaffService{
    private StaffRepository staffRepository;
    public void createStaff(Staff staff){ return staffRepository.save(staff)};
    public List<Staff> getStaff(){
        return staffRepository.findAll();
    }
    public Optional<Staff> getStaffbyID(Long staffID){ return staffRepository.findbyId(staffID);}
    public List<Staff> findByFirstName(String firstName) {
        return staffRepository.findByFirstName(firstName);
    }

    public List<Staff> findByLastName(String lastName) {return staffRepository.findByLastName(lastName);}

    public List<Staff> getStaffByActive(boolean active) {
        return staffRepository.findByActive(active);
    }

    public List<Staff> getStaffbyLastUpdate(){ return staffRepository.find}

    public Optional<Staff> getStaffByEmailAddress(String emailAddress) {return staffRepository.findByEmailAddress(emailAddress);}

    public List<Staff> getStaffByDate(LocalDateTime localDateTime){ return staffRepository.find}
    public Staff updateStaff(Long id, Staff updatedStaff) {
        if (staffRepository.findById(id).isPresent()) {
            Staff existingStaff = staffRepository.findById(id).get();
            existingStaff.setFirstName(updatedStaff.getFirstName());
            existingStaff.setLastName(updatedStaff.getLastName());
            existingStaff.setActive(updatedStaff.isActive());
            existingStaff.setAddress(updatedStaff.getAddress());
            existingStaff.setPaymentList(updatedStaff.getPaymentList());
            existingStaff.setRentalList(updatedStaff.getRentalList());
            existingStaff.setPictureURL(updatedStaff.getPictureURL());
            existingStaff.setLastUpdate(updatedStaff.getLastUpdate());

            return staffRepository.save(existingStaff);
        } else {
            throw new RuntimeException("Staff not found with id " + id);
        }
    }

    public void deleteStaff(Long id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
        } else {
            throw new RuntimeException("Staff not found with id " + id);
        }
    }
}