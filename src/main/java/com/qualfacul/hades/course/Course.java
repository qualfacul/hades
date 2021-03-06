package com.qualfacul.hades.course;

import static javax.persistence.EnumType.STRING;
import static org.apache.commons.lang3.StringUtils.stripAccents;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed
@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Field
	@Boost(1f)
	@Column(name = "name", length = 400, nullable = false)
	private String name;

	@Enumerated(STRING)
	@Column(name = "modality", length = 15, nullable = false)
	private CourseModality modality;

	@Enumerated(STRING)
	@Column(name = "degree", length = 15, nullable = false)
	private CourseDegree degree;

	@OneToMany(mappedBy = "course")
	private List<CourseGrade> courseGrades;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public CourseModality getModality() {
		return modality;
	}

	public CourseDegree getDegree() {
		return degree;
	}

	public List<CourseGrade> getCourseGrades() {
		return courseGrades;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModality(CourseModality modality) {
		this.modality = modality;
	}

	public void setDegree(CourseDegree degree) {
		this.degree = degree;
	}

	public void setCourseGrades(List<CourseGrade> courseGrades) {
		this.courseGrades = courseGrades;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Course))
			return false;

		Course other = (Course) obj;
		return Objects.equals(stripAccents(this.name), stripAccents(other.name))
				&& Objects.equals(this.degree, other.degree) && Objects.equals(this.modality, other.modality);
	}

}