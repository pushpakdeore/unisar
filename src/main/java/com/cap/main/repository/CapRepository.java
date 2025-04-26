package com.cap.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.main.entity.CapForm;
import com.cap.main.entity.User;

@Repository
public interface CapRepository extends JpaRepository<CapForm, Long> {
    Optional<CapForm> findByStudent(User student);
    boolean existsByStudent(User student);
}