package org.example.swe304.Services;

import org.example.swe304.Dtos.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    public CourseDTO findCourseById(Long id);

    CourseDTO save(CourseDTO courseDTO);

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long id);

    void deleteCourse(Long id);

    CourseDTO updateCourseById(Long id, CourseDTO courseDTO);




}
