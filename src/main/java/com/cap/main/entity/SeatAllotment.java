package com.cap.main.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat_allotments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatAllotment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    private College allottedCollege;

    private Integer roundNumber;
    private String allottedCategory;
    private Double studentPercentile;
    private LocalDateTime allotmentDate;

    @Enumerated(EnumType.STRING)
    private AllotmentStatus status;

    public enum AllotmentStatus {
        PROVISIONAL, 
        CONFIRMED, 
        CANCELLED,
        WAITLISTED
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public College getAllottedCollege() {
		return allottedCollege;
	}

	public void setAllottedCollege(College allottedCollege) {
		this.allottedCollege = allottedCollege;
	}

	public Integer getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
	}

	public String getAllottedCategory() {
		return allottedCategory;
	}

	public void setAllottedCategory(String allottedCategory) {
		this.allottedCategory = allottedCategory;
	}

	public Double getStudentPercentile() {
		return studentPercentile;
	}

	public void setStudentPercentile(Double studentPercentile) {
		this.studentPercentile = studentPercentile;
	}

	public LocalDateTime getAllotmentDate() {
		return allotmentDate;
	}

	public void setAllotmentDate(LocalDateTime allotmentDate) {
		this.allotmentDate = allotmentDate;
	}

	public AllotmentStatus getStatus() {
		return status;
	}

	public void setStatus(AllotmentStatus status) {
		this.status = status;
	}

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
    
}