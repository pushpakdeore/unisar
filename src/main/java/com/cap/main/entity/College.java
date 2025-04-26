package com.cap.main.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "colleges")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "college_id")
	private Long collegeId;

	@NotBlank(message = "College name is required")
	@Size(max = 100)
	private String name;

	@NotBlank(message = "Location is required")
	private String location;

	@NotBlank(message = "University name is required")
	private String university;

	@Min(0)
	@Max(100)
	private Integer generalCutoff;

	@Min(0)
	@Max(100)
	private Integer obcCutoff;

	@Min(0)
	@Max(100)
	private Integer scCutoff;

	@Min(0)
	@Max(100)
	private Integer stCutoff;

	@PositiveOrZero(message = "Fees must be positive")
	private Double fees;

	@URL(message = "Invalid website URL")
	private String website;

	@ElementCollection
	@CollectionTable(name = "college_facilities", joinColumns = @JoinColumn(name = "college_id"))
	private Set<String> facilities;

	@Column(name = "available_seats", nullable = false)
	private Integer availableSeats = 0;
	
	@OneToMany(mappedBy = "allottedCollege")
	private List<SeatAllotment> seatAllotments;

	// Remove the duplicate getId()/setId() methods since we're using @Data
	// Keep only the collegeId getters/setters if you need the explicit naming
}