package br.com.rocketseat.apicourse.adapters.output.repositories;

import br.com.rocketseat.apicourse.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
