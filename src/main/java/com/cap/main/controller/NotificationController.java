package com.cap.main.controller;

import java.util.Collections;
import java.util.List;

import com.cap.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.main.entity.Notification;
import com.cap.main.entity.User;
import com.cap.main.service.NotificationService;
import com.cap.main.service.UserService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;
    private final UserRepository userRepository;

    public NotificationController(NotificationService notificationService, UserService userService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Create a notification for a user
    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @RequestParam Long userId,
            @RequestParam String message) {
        User user = (User) userRepository.findAllById(Collections.singleton(userId));
        return ResponseEntity.ok(notificationService.createNotification(user, message));
    }

    // Get all notifications for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }

    // ✅ Get a single notification by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
