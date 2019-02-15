package com.example.Assignment1.services;

import com.example.Assignment1.model.Lesson;
import com.example.Assignment1.model.Module;
import com.example.Assignment1.model.Topic;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TopicService {
  List<Topic> topics = new ArrayList<>();
  LessonService lessonService = new LessonService();

  @PostMapping("/api/modules/{moduleId}/lessons")
  public Topic createTopic(@PathVariable("lessonId") String lessonId, @RequestBody Topic topic) {
    Lesson lesson = lessonService.findLessonById(lessonId);
    lesson.getTopics().add(topic);
    topics.add(topic);
    return topic;
  }

  @GetMapping("/api/modules/{moduleId}/lessons")
  public List findAllTopics(@PathVariable("lessonId") String lessonId) {
    Lesson lesson = lessonService.findLessonById(lessonId);
    return lesson.getTopics();
  }

  @GetMapping("/api/topics/{topicId}")
  public Topic findTopicById(@PathVariable("topicId") String topicId) {
    for(Topic topic : topics) {
      if(topic.getId().equals(topicId)) {
        return topic;
      }
    }
    return null;
  }

  @PutMapping("/api/topics/{topicId}")
  public Topic updateTopic(@PathVariable("topicId") String topicId, @RequestBody Topic topic) {
    for(Topic top : topics) {
      if(top.getId().equals(topicId)) {
        top.setTitle(topic.getTitle());
        return top;
      }
    }
    return null;
  }

  @DeleteMapping(" /api/topics/{topicId}")
  public void deleteTopic(@PathVariable("topicId") String topicId) {
    for(Topic topic : topics) {
      if(topic.getId().equals(topicId)) {
        topics.remove(topic);
        for(Lesson lesson: lessonService.lessons) {
          if(lesson.getTopics().contains(topic)) {
            lesson.getTopics().remove(topic);
          }
        }
        return;
      }
    }

  }
}
