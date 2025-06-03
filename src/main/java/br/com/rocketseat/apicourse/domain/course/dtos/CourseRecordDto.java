package br.com.rocketseat.apicourse.domain.course.dtos;

import jakarta.validation.constraints.NotBlank;

public record CourseRecordDto(
        @NotBlank(message = "Course name cannot be blank")
        String name,

        @NotBlank(message = "Course category cannot be blank")
        String category) {
}
