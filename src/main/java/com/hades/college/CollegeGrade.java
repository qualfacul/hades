package com.hades.college;

import static javax.persistence.CascadeType.ALL;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hades.user.User;
import com.hermes.college.GradeType;

@Entity
@Table(name = "college_grade")
public class CollegeGrade {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(cascade = ALL)
	private College college;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "grade_origin", nullable = false)
	private GradeType gradeType;
	
	@Column(name = "date")
	private Calendar date;
	
	@Column(name = "value")
	private Double value;
	
	@OneToOne(optional = true)
	private User user;
	
	@Deprecated //Hibernate eyes only
	public CollegeGrade() {
	}
	
	public CollegeGrade(College college, GradeType gradeType, Calendar date, Double value) {
		this.college = college;
		this.gradeType = gradeType;
		this.date = date;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public GradeType getGradeOrigin() {
		return gradeType;
	}

	public void setGradeOrigin(GradeType gradeOrigin) {
		this.gradeType = gradeOrigin;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}