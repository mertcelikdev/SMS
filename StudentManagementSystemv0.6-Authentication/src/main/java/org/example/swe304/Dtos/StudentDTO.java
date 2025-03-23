package org.example.swe304.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.swe304.Enums.Gender;
import java.time.LocalDate;

@Data
public class StudentDTO {

    @NotNull(message = "İsim boş olamaz")
    @NotBlank(message = "İsim boş olamaz")
    private String firstName;

    @NotNull(message = "Soyisim boş olamaz")
    @NotBlank(message = "Soyisim boş olamaz")
    private String lastName;

    @NotNull(message = "Öğrenci numarası boş olamaz")
    @NotBlank(message = "Öğrenci numarası boş olamaz")
    private String studentNumber;

    @NotNull(message = "Email boş olamaz")
    @NotBlank(message = "Email boş olamaz")
    private String email;

    @NotNull(message = "Adres boş olamaz")
    @NotBlank(message = "Adres boş olamaz")
    private String address;

    @NotNull(message = "Doğum tarihi boş olamaz")
    private LocalDate birthDate;

    @NotNull(message = "Cinsiyet boş olamaz")
    private Gender gender;

    // Parametreli yapıcı metod
    public StudentDTO(String firstName, String lastName, String studentNumber, String email, String address, LocalDate birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public StudentDTO() {
    }

    public @NotNull(message = "İsim boş olamaz") @NotBlank(message = "İsim boş olamaz") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "İsim boş olamaz") @NotBlank(message = "İsim boş olamaz") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "Soyisim boş olamaz") @NotBlank(message = "Soyisim boş olamaz") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "Soyisim boş olamaz") @NotBlank(message = "Soyisim boş olamaz") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "Öğrenci numarası boş olamaz") @NotBlank(message = "Öğrenci numarası boş olamaz") String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(@NotNull(message = "Öğrenci numarası boş olamaz") @NotBlank(message = "Öğrenci numarası boş olamaz") String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public @NotNull(message = "Email boş olamaz") @NotBlank(message = "Email boş olamaz") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email boş olamaz") @NotBlank(message = "Email boş olamaz") String email) {
        this.email = email;
    }

    public @NotNull(message = "Doğum tarihi boş olamaz") LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull(message = "Doğum tarihi boş olamaz") LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @NotNull(message = "Cinsiyet boş olamaz") Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "Cinsiyet boş olamaz") Gender gender) {
        this.gender = gender;
    }

    public @NotNull(message = "Adres boş olamaz") @NotBlank(message = "Adres boş olamaz") String getAddress() {
        return address;
    }

    public void setAddress(@NotNull(message = "Adres boş olamaz") @NotBlank(message = "Adres boş olamaz") String address) {
        this.address = address;
    }
}