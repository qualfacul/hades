package com.qualfacul.hades.college.rank;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qualfacul.hades.college.College;

@Entity
@Table(name = "college_rank")
public class CollegeRank {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private College college;

	@Column(name = "grade")
	private double grade;

	@Enumerated(EnumType.STRING)
	@Column(name = "rank_type")
	private CollegeRankType rankType;

	@Column(name = "grades_quantity")
	private int gradesQuantity;

	@Column(name = "created_at")
	private LocalDate createdAt = LocalDate.now();

	@Deprecated // Hibernate eyes
	CollegeRank() {
	}

	public CollegeRank(College college, double grade, CollegeRankType rankType, int gradesQuantity) {
		this.college = college;
		this.grade = grade;
		this.rankType = rankType;
		this.gradesQuantity = gradesQuantity;
		this.createdAt = LocalDate.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public CollegeRankType getRankType() {
		return rankType;
	}

	public void setRankType(CollegeRankType rankType) {
		this.rankType = rankType;
	}

	public int getGradesQuantity() {
		return gradesQuantity;
	}

	public void setGradesQuantity(int gradesQuantity) {
		this.gradesQuantity = gradesQuantity;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

}
