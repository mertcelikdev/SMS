package org.example.swe304.Controller;

import org.example.swe304.Model.Registration;
import org.example.swe304.Repository.CourseRepository;
import org.example.swe304.Repository.RegistrationRepository;
import org.example.swe304.Repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public RegistrationController(RegistrationRepository registrationRepository,
                                  StudentRepository studentRepository,
                                  CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // 📌 Kayıtları Listeleme (Sayfalama ve Sıralama ile)
    @GetMapping
    public String listRegistrations(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    @RequestParam(defaultValue = "student_id") String sortBy,
                                    @RequestParam(defaultValue = "asc") String direction,
                                    Model model) {

        // Sıralama parametrelerini kontrol et
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        // Kayıtları sayfalı şekilde çek
        Page<Registration> registrationPage = registrationRepository.findAll(pageable);

        model.addAttribute("registrations", registrationPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", registrationPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "Registration/Registration"; // 📌 Listeleme sayfasına yönlendirme
    }

    // 📌 Yeni Kayıt Formunu Açma
    @GetMapping("/add")
    public String showRegistrationForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("registration", new Registration());
        return "Registration/RegistrationAdd"; // 📌 Yeni kayıt ekleme formuna yönlendirme
    }

    // 📌 Yeni Kayıt Ekleme
    @PostMapping
    public String registerStudent(@ModelAttribute Registration registration) {
        registrationRepository.save(registration);
        return "redirect:/registration";
    }

    // 📌 Kayıt Güncelleme
    @GetMapping("/update/{id}")
    public String editRegistration(@PathVariable Long id, Model model) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kayıt bulunamadı! ID: " + id));

        model.addAttribute("registration", registration);
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "Registration/RegistrationUpdate"; // 📌 Güncelleme formuna yönlendirme
    }

    @PostMapping("/update/{id}")
    public String updateRegistration(@PathVariable Long id, @ModelAttribute Registration registration) {
        Registration existingRegistration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Güncellenecek kayıt bulunamadı! ID: " + id));

        existingRegistration.setStudent(registration.getStudent());
        existingRegistration.setCourse(registration.getCourse());

        registrationRepository.save(existingRegistration);
        return "redirect:/registration";
    }

    // 📌 Kayıt Silme
    @GetMapping("/delete/{id}")
    public String deleteRegistration(@PathVariable Long id) {
        registrationRepository.deleteById(id);
        return "redirect:/registration";
    }
}
