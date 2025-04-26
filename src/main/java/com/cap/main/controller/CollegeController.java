package com.cap.main.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.main.entity.College;
import com.cap.main.service.CollegeServices;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {
    
    private final CollegeServices collegeService;
    

    public CollegeController(CollegeServices collegeService) {
		super();
		this.collegeService = collegeService;
	}

	@GetMapping
    public ResponseEntity<Page<College>> getAllColleges(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String university,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return ResponseEntity.ok(collegeService.searchColleges(
            name, location, university, null, null, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<College> getCollegeById(@PathVariable Long id) {
        return ResponseEntity.ok(collegeService.getCollegeById(id));
    }

    @GetMapping("/locations")
    public ResponseEntity<List<String>> getAllLocations() {
        return ResponseEntity.ok(collegeService.getAllLocations());
    }

    @GetMapping("/universities")
    public ResponseEntity<List<String>> getAllUniversities() {
        return ResponseEntity.ok(collegeService.getAllUniversities());
    }
}