package br.com.rocketseat.apicourse.adapters.input.controllers;

import br.com.rocketseat.apicourse.application.usecases.CourseService;
import br.com.rocketseat.apicourse.domain.course.Course;
import br.com.rocketseat.apicourse.domain.course.dtos.CourseRecordDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable(value = "courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findById(courseId).get());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
    }

    @PatchMapping("/{courseId}/active")
    public ResponseEntity<Object> updateCourseStatus(@PathVariable(value = "courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourseStatus(courseId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCourse(@RequestBody @Valid CourseRecordDto courseRecordDto) {
        if (courseService.existsByName(courseRecordDto.name())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Course name is already taken!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRecordDto));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId, @RequestBody CourseRecordDto courseRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseId, courseRecordDto));
    }

}
