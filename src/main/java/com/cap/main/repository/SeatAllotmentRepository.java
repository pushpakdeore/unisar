package com.cap.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cap.main.entity.College;
import com.cap.main.entity.SeatAllotment;
import com.cap.main.entity.SeatAllotment.AllotmentStatus;

@Repository
public interface SeatAllotmentRepository extends JpaRepository<SeatAllotment, Long> {

    // Option 1: Query by College entity
    List<SeatAllotment> findByAllottedCollegeAndStatus(College college, AllotmentStatus status);

    // Option 2: Query by college ID
    @Query("SELECT sa FROM SeatAllotment sa WHERE sa.allottedCollege.collegeId = :collegeId AND sa.status = :status")
    List<SeatAllotment> findByAllottedCollegeIdAndStatus(@Param("collegeId") Long collegeId, 
                                                       @Param("status") AllotmentStatus status);

    // Other useful methods
    List<SeatAllotment> findByStudentId(Long studentId);
    
    List<SeatAllotment> findByStatus(AllotmentStatus status);
}