package com.cap.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.main.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

       Optional<User> findByEmail(String email);
       List<User> findByIsActiveTrueAndOtpVerifiedTrueAndLatestPercentileIsNotNull();
       List<User> findByLatestPercentileGreaterThanEqual(Double percentile);

}