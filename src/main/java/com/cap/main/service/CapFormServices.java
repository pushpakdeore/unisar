package com.cap.main.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cap.main.entity.CapForm;
import com.cap.main.entity.User;
import com.cap.main.exception.ResourceNotFoundException;
import com.cap.main.exception.ValidationException;
import com.cap.main.repository.CapRepository;
import com.cap.main.repository.CollegeRepository;

@Service
public class CapFormServices {

	private final CapRepository capFormRepository;
	private final CollegeRepository collegeRepository;

	public CapFormServices(CapRepository capFormRepository, CollegeRepository collegeRepository) {
		super();
		this.capFormRepository = capFormRepository;
		this.collegeRepository = collegeRepository;
	}

	@Transactional
	public CapForm saveOrUpdateForm(User student, Map<Long, Integer> preferences) {
		validatePreferences(preferences);

		CapForm form = capFormRepository.findByStudent(student).orElseGet(() -> new CapForm());

		form.setStudent(student);
		form.setCollegePreferences(preferences);

		return capFormRepository.save(form);
	}

	public CapForm getFormByStudent(User student) {
		return capFormRepository.findByStudent(student).orElseThrow(() -> new ResourceNotFoundException(
				String.format("CAP form not found for student ID: %d", student.getId())));
	}

	@Transactional
	public CapForm submitForm(User student) {
		CapForm form = getFormByStudent(student);

		if (form.getCollegePreferences().isEmpty()) {
			throw new ValidationException("Cannot submit empty CAP form");
		}

		form.setSubmitted(true);
		form.setSubmissionDate(LocalDateTime.now());

		return capFormRepository.save(form);
	}

	private void validatePreferences(Map<Long, Integer> preferences) {
		if (preferences == null || preferences.isEmpty()) {
			throw new ValidationException("College preferences cannot be empty");
		}

		preferences.keySet().forEach(collegeId -> {
			if (!collegeRepository.existsById(collegeId)) {
				throw new ValidationException(String.format("College with ID %d does not exist", collegeId));
			}
		});
	}
}