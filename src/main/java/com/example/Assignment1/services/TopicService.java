package com.example.Assignment1.services;

import com.example.Assignment1.model.Lesson;
import com.example.Assignment1.model.Topic;
import com.example.Assignment1.model.Widget;
import com.example.Assignment1.repositories.LessonRepository;
import com.example.Assignment1.repositories.TopicRepository;
import com.example.Assignment1.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class TopicService {
  List<Topic> topics = new ArrayList<>();
  LessonService lessonService = new LessonService();
  CourseService courseService = new CourseService();

  @Autowired
  TopicRepository topicRepo;
  @Autowired
  LessonRepository lessonRepo;
  @Autowired
  WidgetRepository widgetRepo;

  @PostMapping("/api/lessons/{lessonId}/topics")
  public Topic createTopic(@PathVariable("lessonId") int lessonId, @RequestBody Topic topic) {
    //Lesson lesson = lessonService.findLessonById(lessonId);
//    Lesson lesson = lessonRepo.findById(lessonId).get();
//    lesson.getTopics().add(topic);
//    topics.add(topic);
    topic.setLesson(lessonRepo.findById(lessonId).get());
    return topicRepo.save(topic);
  }

  @GetMapping("/api/lessons/{lessonId}/topics")
  public List findAllTopics(@PathVariable("lessonId") int lessonId) {
    //Lesson lesson = LessonService.findLessonById(lessonId);
//    Lesson lesson = lessonRepo.findById(lessonId).get();
//    return lesson.getTopics();
    return topicRepo.findTopicByLesson(lessonId);
  }

  @GetMapping("/api/topics/{topicId}")
  public Optional<Topic> findTopicById(@PathVariable("topicId") int topicId) {
//    for(Course course : courseService.findAllCourses()) {
//      for(Module module : course.getModules()) {
//        for(Lesson lesson : module.getLessons()) {
//          for(Topic topic : lesson.getTopics()) {
//            if (topic.getId().equals(topicId)) {
//              return topic;
//            }
//          }
//
//        }
//      }
//    }
    return topicRepo.findById(topicId);
  }

  @PutMapping("/api/topics/{topicId}")
  public Topic updateTopic(@PathVariable("topicId") int topicId, @RequestBody Topic topic) {
//    for(Topic top : topics) {
//      if(top.getId().equals(topicId)) {
//        top.setTitle(topic.getTitle());
//        return top;
//      }
//    }
    Topic t = topicRepo.findById(topicId).get();
    t.setTitle(topic.getTitle());
    return topicRepo.save(t);
  }

  @DeleteMapping("/api/topics/{topicId}")
  public void deleteTopic(@PathVariable("topicId") int topicId) {
//    for(Topic topic : topics) {
//      if(topic.getId().equals(topicId)) {
//        topics.remove(topic);
//        for(Lesson lesson: lessonService.lessons) {
//          if(lesson.getTopics().contains(topic)) {
//            lesson.getTopics().remove(topic);
//          }
//        }
//        return;
//      }
//    }
    topicRepo.deleteById(topicId);
  }

  @CrossOrigin(allowCredentials = "true")
  @PostMapping("/api/topics/{topicId}/widget")
  public Widget createWidget(@PathVariable("topicId") int topicId,
                             @RequestBody Widget widget) {
    widget.setTopic(topicRepo.findById(topicId).get());
    return widgetRepo.save(widget);
  }

  @CrossOrigin(allowCredentials = "true")
  @GetMapping("/api/topics/{topicId}/widget")
  public List<Widget> findAllWidgets(@PathVariable("topicId") int topicId) {
    return widgetRepo.findWidgetByTopic(topicId);
  }


}
