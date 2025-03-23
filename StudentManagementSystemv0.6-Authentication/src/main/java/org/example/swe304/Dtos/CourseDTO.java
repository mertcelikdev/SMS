package org.example.swe304.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
public class CourseDTO {

    @NotNull(message = "Ders adı boş olamaz")
    @NotBlank(message = "Ders adı boş olamaz")
    @Size(min = 2, max = 100, message = "Ders adı en az 2 en fazla 100 karakter olabilir")
    private String name;

    @NotNull(message = "Ders kodu boş olamaz")
    @NotBlank(message = "Ders kodu boş olamaz")
    @Size(min = 2, max = 10, message = "Ders kodu en az 2 en fazla 10 karakter olabilir")
    private String code;

    @Min(value = 1, message = "Kredi en az 1 olmalıdır")
    @Max(value = 10, message = "Kredi en fazla 10 olabilir")
    private int credit;

    @NotNull(message = "Öğretim üyesi adı boş olamaz")
    @NotBlank(message = "Öğretim üyesi adı boş olamaz")
    @Size(min = 2, max = 100, message = "Öğretim üyesi adı en az 2 en fazla 100 karakter olabilir")
    private String lecturerName;

    @NotNull(message = "Ders tarihi boş olamaz")
    @NotBlank(message = "Ders tarihi boş olamaz")
    private String courseDate;

    public CourseDTO(String name, String code, int credit, String lecturerName, String courseDate) {
        this.name = name;
        this.code = code;
        this.credit = credit;
        this.lecturerName = lecturerName;
        this.courseDate = courseDate;
    }

    public CourseDTO() {
    }
}