package org.example.swe304.Controller;

import jakarta.validation.Valid;
import org.example.swe304.Dtos.CourseDTO;
import org.example.swe304.Model.Course;
import org.example.swe304.Repository.CourseRepository;
import org.example.swe304.Services.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;

    public CourseController(CourseRepository courseRepository , CourseService courseService) {
        this.courseService=courseService;
        this.courseRepository = courseRepository;
    }



    @GetMapping
    public String listCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Course> coursePage = courseRepository.findAll(pageable);

        model.addAttribute("courses", coursePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coursePage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "Course/Course"; // Güncellenmiş Thymeleaf sayfasına yönlendirme
    }



    @GetMapping("/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "Course/CourseAdd";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        courseRepository.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/update/{id}")
    public String updateCourseForm(@PathVariable Long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı! ID: " + id));
        model.addAttribute("course", course);
        return "Course/CourseUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Güncellenecek ders bulunamadı! ID: " + id));

        existingCourse.setName(course.getName());
        existingCourse.setCode(course.getCode());
        existingCourse.setCredit(course.getCredit());

        courseRepository.save(existingCourse);
        return "redirect:/courses";
    }

    // delete metodunu oluştur
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return "redirect:/courses";
    }



    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<List<CourseDTO>> getAllCoursesJson() {
        List<CourseDTO> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/json/add")
    @ResponseBody
    public ResponseEntity<CourseDTO> addCourseJson(@Valid @RequestBody CourseDTO courseDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        CourseDTO savedCourse = courseService.save(courseDTO);
        return ResponseEntity.status(201).body(savedCourse);
    }

    @PutMapping("/json/update/{id}")
    @ResponseBody
    public ResponseEntity<CourseDTO> updateCourseJson(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        CourseDTO existingCourse = courseService.getCourseById(id);
        if (existingCourse == null) {
            return ResponseEntity.notFound().build();
        }
        CourseDTO updatedCourse = new CourseDTO();
        BeanUtils.copyProperties(courseDTO, updatedCourse);
        CourseDTO updatedCourseDTO = courseService.updateCourseById(id, updatedCourse);
        return ResponseEntity.ok(updatedCourseDTO);
    }

    @DeleteMapping("/json/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteCourseJson(@PathVariable Long id) {
        if (courseService.getCourseById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        CourseDTO dto = new CourseDTO(
                course.getName(),
                course.getCode(),
                course.getCredit(),
                course.getLecturerName(),
                course.getCourseDate()
        );

        return ResponseEntity.ok(dto);
    }

}


