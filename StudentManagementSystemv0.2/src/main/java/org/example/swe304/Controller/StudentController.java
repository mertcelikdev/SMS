package org.example.swe304.Controller;

import org.example.swe304.Model.Student;
import org.example.swe304.Repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 📌 Tüm öğrencileri listeleme
    @GetMapping
    public String listStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> studentPage = studentRepository.findAll(pageable);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "Student/Student"; // Güncellenmiş Thymeleaf sayfasına yönlendirme
    }

    // 📌 Yeni öğrenci ekleme formu
    @GetMapping("/add")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "Student/StudentAdd";
    }

    // 📌 Yeni öğrenci kaydetme işlemi
    @PostMapping("/add")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // 📌 Öğrenci güncelleme formunu açma
    @GetMapping("/update/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı! ID: " + id));

        model.addAttribute("student", student);
        return "Student/StudentUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Güncellenecek öğrenci bulunamadı! ID: " + id));

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setStudentNumber(student.getStudentNumber());
        existingStudent.setGrade(student.getGrade());

        studentRepository.save(existingStudent);
        return "redirect:/students";
    }

    // 📌 Öğrenci silme işlemi
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Silinecek öğrenci bulunamadı! ID: " + id);
        }
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}
