package org.example.swe304.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "registration")

public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long registration_id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(Long registration_id) {
        this.registration_id = registration_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Registration(Long registration_id, Student student, Course course) {
        this.registration_id = registration_id;
        this.student = student;
        this.course = course;
    }

    public Registration() {
    }
}
