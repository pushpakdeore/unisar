package com.cap.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cap.main.entity.User;
import com.cap.main.exception.UserNotEligibleException;
import com.cap.main.repository.UserRepository;

@Service
public class SeatAllotmentService {

    private final UserRepository userRepository;

    @Autowired
    public SeatAllotmentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Allocates a seat to a user if they meet all eligibility criteria
     * @param userId ID of the user to allocate seat to
     * @return true if seat was allocated successfully
     * @throws UserNotEligibleException if user is not eligible for admission
     */
    @Transactional
    public boolean allocateSeat(Long userId) throws UserNotEligibleException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        if (!user.isEligibleForAdmission()) {
            throw new UserNotEligibleException("User does not meet admission criteria");
        }
        
        // Additional business logic for seat allocation
        // For example: checking available seats, percentile cutoff, etc.
        
        // Mark seat as allocated (you might want to add a field in User for this)
        // user.setSeatAllocated(true);
        
        userRepository.save(user);
        return true;
    }

    /**
     * Gets all users eligible for seat allotment based on their percentile and status
     * @return List of eligible users
     */
    public List<User> getEligibleUsers() {
        return userRepository.findByIsActiveTrueAndOtpVerifiedTrueAndLatestPercentileIsNotNull();
    }

    /**
     * Gets users above a certain percentile threshold
     * @param cutoffPercentile the minimum percentile required
     * @return List of qualifying users
     */
    public List<User> getUsersAbovePercentile(Double cutoffPercentile) {
        return userRepository.findByLatestPercentileGreaterThanEqual(cutoffPercentile);
    }

    /**
     * Batch allotment process for multiple users
     * @param userIds List of user IDs to process
     * @return count of successfully allotted seats
     */
    @Transactional
    public int batchAllotSeats(List<Long> userIds) {
        int allottedCount = 0;
        
        for (Long userId : userIds) {
            try {
                if (allocateSeat(userId)) {
                    allottedCount++;
                }
            } catch (UserNotEligibleException e) {
                // Log the exception and continue with next user
            }
        }
        
        return allottedCount;
    }
}