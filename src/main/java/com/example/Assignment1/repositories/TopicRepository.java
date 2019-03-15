package com.example.Assignment1.repositories;

import com.example.Assignment1.model.Topic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
  @Query("SELECT topic FROM Topic topic WHERE topic.lesson.id=:lesson_id")
  public List<Topic> findTopicByLesson (
          @Param("lesson_id") int lesson_id);
}
