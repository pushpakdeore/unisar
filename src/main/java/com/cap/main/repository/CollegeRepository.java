package com.cap.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cap.main.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

    // Standard method already provided by JpaRepository
    // Optional<College> findById(Long id);
    
    // Find by collegeId (same as above since collegeId is the @Id)
    default Optional<College> findByCollegeId(Long collegeId) {
        return findById(collegeId);
    }

    List<College> findByName(String name);

    // Fixed to use availableSeats instead of seats
    List<College> findByAvailableSeatsGreaterThan(int seats);

    List<College> findByNameContainingIgnoreCase(String name);

    List<College> findByLocation(String location);

    @Query("SELECT DISTINCT c.location FROM College c ORDER BY c.location")
    List<String> findAllUniqueLocations();

    @Query("SELECT DISTINCT c.university FROM College c ORDER BY c.university")
    List<String> findAllUniqueUniversities();

    // Remove this until you properly define the relationship
    // @Query("SELECT c FROM College c JOIN c.users u WHERE u.id = :studentId")
    // List<College> findCollegesByStudentId(@Param("studentId") Long studentId);

    // Fixed to use availableSeats
    @Query("SELECT c FROM College c WHERE c.availableSeats > :minSeats")
    List<College> findCollegesWithSufficientSeats(@Param("minSeats") int minSeats);
}