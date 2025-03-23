package org.example.swe304.Controller;

import org.example.swe304.Model.Course;
import org.example.swe304.Model.Registration;
import org.example.swe304.Repository.RegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final RegistrationRepository registrationRepository;

    public ScheduleController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @GetMapping("/{studentId}")
    public String getStudentSchedule(@PathVariable("studentId") Long studentId, Model model) {
        List<Registration> registrations = registrationRepository.findByStudentId(studentId);

        // Eğer kayıt yoksa, boş bir liste gönderelim
        List<Course> courses = registrations.stream()
                .map(Registration::getCourse)
                .collect(Collectors.toList());

        model.addAttribute("schedules", registrations); // Thymeleaf'in beklediği değişken adıyla ekliyoruz
        model.addAttribute("studentId", studentId);

        return "Schedule/Index"; // schedule.html sayfasına yönlendir
    }
}
