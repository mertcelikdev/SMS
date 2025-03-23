package org.example.swe304.Model;

import jakarta.persistence.*;
import lombok.*;
import org.example.swe304.Enums.Gender;

import java.time.LocalDate;
import java.util.Date;



@Entity

@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "studentnumber")
    private String studentNumber;
    @Column(name = "grade")
    private int grade;
    @Column(name = "email")
    private String email;
    @Column(name = "birthdate")
    private LocalDate birthDate;
    @Column(name = "address")
    private String address;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "identitynumber")
    private String identityNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Student(Long id, String firstName, String lastName, String studentNumber, int grade, String email, LocalDate birthDate, String address, String phoneNumber, String identityNumber, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.identityNumber = identityNumber;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Student() {
    }
}
