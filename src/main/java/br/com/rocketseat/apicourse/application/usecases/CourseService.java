package br.com.rocketseat.apicourse.application.usecases;

import br.com.rocketseat.apicourse.domain.course.Course;
import br.com.rocketseat.apicourse.domain.course.dtos.CourseRecordDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> findById(UUID courseId);

    void deleteCourse(UUID courseId);

    Course updateCourseStatus(UUID courseId);

    Course createCourse(CourseRecordDto courseRecordDto);

    boolean existsByName(String name);

    Course updateCourse(UUID courseId, CourseRecordDto courseRecordDto);
}
