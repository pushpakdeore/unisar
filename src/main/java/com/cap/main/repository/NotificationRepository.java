package com.cap.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.main.entity.Notification;
import com.cap.main.entity.User;

/**
 * NotificationRepository provides CRUD operations for Notification entities. It
 * extends JpaRepository for easy database interaction.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	/**
	 * Find all notifications for a specific user.
	 *
	 * @param userId the ID of the user.
	 * @return a list of notifications for the specified user.
	 */

	List<Notification> findByUser(User user);

	List<Notification> findByUserId(Long userId);

}