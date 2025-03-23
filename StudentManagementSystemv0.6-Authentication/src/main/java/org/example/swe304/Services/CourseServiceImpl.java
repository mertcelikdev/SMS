package org.example.swe304.Services;

import org.example.swe304.Dtos.CourseDTO;
import org.example.swe304.Model.Course;
import org.example.swe304.Repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO findCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return null;
        }
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }

    @Override
    public CourseDTO save(CourseDTO courseDTOUi) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTOUi, course);
        Course savedCourse = courseRepository.save(course);
        BeanUtils.copyProperties(savedCourse, courseDTOUi);
        return courseDTOUi;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    CourseDTO courseDTO = new CourseDTO();
                    BeanUtils.copyProperties(course, courseDTO);
                    return courseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return null;
        }
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO updateCourseById(Long id, CourseDTO updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            BeanUtils.copyProperties(updatedCourse, course);
            Course savedCourse = courseRepository.save(course);
            CourseDTO courseDTOUi = new CourseDTO();
            BeanUtils.copyProperties(savedCourse, courseDTOUi);
            return courseDTOUi;
        }).orElse(null);
    }
}