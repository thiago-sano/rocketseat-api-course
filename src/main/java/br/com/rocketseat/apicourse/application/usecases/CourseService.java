package br.com.rocketseat.apicourse.application.usecases;

import br.com.rocketseat.apicourse.domain.course.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
}
