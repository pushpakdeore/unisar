package com.cap.main.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cap.main.entity.College;
import com.cap.main.exception.ResourceNotFoundException;
import com.cap.main.repository.CollegeRepository;

@Service
public class CollegeServices {
    
    private final CollegeRepository collegeRepository;
    

    public CollegeServices(CollegeRepository collegeRepository) {
		super();
		this.collegeRepository = collegeRepository;
	}

	public Page<College> searchColleges(String name, String location, String university, 
                                      Integer maxCutoff, Double minFees, Pageable pageable) {
        
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreCase();
        
        College probe = new College();
        probe.setName(name);
        probe.setLocation(location);
        probe.setUniversity(university);
        
        Example<College> example = Example.of(probe, matcher);
        
        return collegeRepository.findAll(example, pageable);
    }

    public College getCollegeById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid college ID");
        }
        
        return collegeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    String.format("College with ID %d not found", id)
                ));
    }

    public List<String> getAllLocations() {
        return collegeRepository.findAllUniqueLocations();
    }

    public List<String> getAllUniversities() {
        return collegeRepository.findAllUniqueUniversities();
    }
}