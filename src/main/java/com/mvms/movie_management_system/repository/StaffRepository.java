package com.mvms.movie_management_system.repository;

import com.mvms.movie_management_system.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
@Repository
public interface StaffRepository extends JpaRepository<Staff,Long>{
    List <Staff> findbyFirstName(String firstName);
    List<Staff> findbyLastName(String lastName);
    List<Staff> findbyActive (Boolean active);
    List<Staff> findbyLastUpdate (LocalDateTime localDateTime);
    List<Staff> findbyPictureURL (String pictureURL);

    @Query("SELECT s FROM Staff s WHERE s.email = ?1")
    Optional<Staff> findbyEmail(String email);
}