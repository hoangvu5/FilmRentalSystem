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
}