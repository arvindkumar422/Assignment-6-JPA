package com.example.Assignment1.repositories;

import com.example.Assignment1.model.Lesson;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
  @Query("SELECT lesson FROM Lesson lesson WHERE lesson.module.id=:module_id")
  public List<Lesson> findLessonByModule (
          @Param("module_id") int module_id);
}
