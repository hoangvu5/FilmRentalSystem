package com.mvms.movie_management_system.repository;

import com.mvms.movie_management_system.entity.Customer;
import com.mvms.movie_management_system.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByGender(Gender gender);

    List<Customer> findByActive(boolean active);

    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Optional<Customer> findByEmailAddress(String emailAddress);
}
