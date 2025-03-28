package org.example.swe304.Services;

import org.example.swe304.Dtos.StudentDTO;
import org.example.swe304.Model.Student;
import org.example.swe304.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO save(StudentDTO studentDTOui) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTOui, student);
        Student savedStudent = studentRepository.save(student);
        BeanUtils.copyProperties(savedStudent, studentDTOui);
        return studentDTOui;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> {
                    StudentDTO studentDTO = new StudentDTO();
                    BeanUtils.copyProperties(student, studentDTO);
                    return studentDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            BeanUtils.copyProperties(updatedStudent, student);
            Student savedStudent = studentRepository.save(student);
            StudentDTO studentDTOui = new StudentDTO();
            BeanUtils.copyProperties(savedStudent, studentDTOui);
            return studentDTOui;
        }).orElse(null);
    }


    @Override
    public void validateBirthDate(StudentDTO studentDTO) {
        if (studentDTO.getBirthDate() == null) {
            throw new IllegalArgumentException("Doğum tarihi boş olamaz");
        }

        if (studentDTO.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Doğum tarihi bugünden ileri bir tarih olamaz");
        }
    }
}