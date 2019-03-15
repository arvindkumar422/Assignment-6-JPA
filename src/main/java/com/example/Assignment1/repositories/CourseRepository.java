package com.example.Assignment1.repositories;

import com.example.Assignment1.model.Course;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
