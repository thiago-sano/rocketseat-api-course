package br.com.rocketseat.apicourse.application.services;

import br.com.rocketseat.apicourse.adapters.output.repositories.CourseRepository;
import br.com.rocketseat.apicourse.application.exceptions.NotFoundException;
import br.com.rocketseat.apicourse.application.usecases.CourseService;
import br.com.rocketseat.apicourse.domain.course.Course;
import br.com.rocketseat.apicourse.domain.course.dtos.CourseRecordDto;
import br.com.rocketseat.apicourse.domain.course.enums.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public void deleteCourse(UUID courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isEmpty()){
            throw new NotFoundException("Error: course not found");
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course updateCourseStatus(UUID courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("Error: course not found");
        } else if (courseOptional.get().getStatus() == Status.ACTIVE) {
            courseOptional.get().setStatus(Status.INACTIVE);
        } else {
            courseOptional.get().setStatus(Status.ACTIVE);
        }
        return courseRepository.save(courseOptional.get());
    }

    @Override
    public Course createCourse(CourseRecordDto courseRecordDto) {
        var course = new Course();
        BeanUtils.copyProperties(courseRecordDto, course);
        course.setStatus(Status.ACTIVE);
        return courseRepository.save(course);
    }

    @Override
    public boolean existsByName(String name) {
        return courseRepository.existsByName(name);
    }
}
