package br.com.rocketseat.apicourse.application.usecases;

import br.com.rocketseat.apicourse.domain.course.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> findById(UUID courseId);
}
