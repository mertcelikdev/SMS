package org.example.swe304.Services;

import org.example.swe304.Dtos.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO save(StudentDTO student);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    void deleteStudent(Long id);

    StudentDTO updateStudent(Long id, StudentDTO studentDTOui);

    void validateBirthDate(StudentDTO studentDTOui);
}