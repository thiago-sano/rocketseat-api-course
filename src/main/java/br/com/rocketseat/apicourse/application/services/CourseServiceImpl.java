package br.com.rocketseat.apicourse.application.services;

import br.com.rocketseat.apicourse.adapters.output.repositories.CourseRepository;
import br.com.rocketseat.apicourse.application.exceptions.NotFoundException;
import br.com.rocketseat.apicourse.application.usecases.CourseService;
import br.com.rocketseat.apicourse.domain.course.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(UUID courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isEmpty()){
            throw new NotFoundException("Error: course not found");
        }
        return courseOptional;
    }
}
