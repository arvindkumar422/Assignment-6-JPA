package com.example.Assignment1.services;

import com.example.Assignment1.model.Course;
import com.example.Assignment1.model.Lesson;
import com.example.Assignment1.model.Module;
import com.example.Assignment1.model.Topic;
import com.example.Assignment1.model.Widget;

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
import java.util.Random;

@RestController
@CrossOrigin(origins = "https://webservices-5.herokuapp.com")

public class CourseService {
  static List<Course> courses = new ArrayList<>();
  List<Widget> widgets = new ArrayList<>();
  List<Topic> topics = new ArrayList<>();
  List<Lesson> lessons = new ArrayList<>();
  List<Module> modules = new ArrayList<>();
  Random rand = new Random();
  {
//    Widget widget = new Widget("1", "LIST","top_end");
//    widgets.add(widget);
//    Topic topic = new Topic("2", "topic1", widgets);
//    topics.add(topic);
//    Lesson lesson = new Lesson("3", "lesson1", topics);
//    lessons.add(lesson);
//    Module module = new Module("4", "module1", lessons);
//    modules.add(module);
//    Course course = new Course("5", "course1", modules);
//    courses.add(course);
  }

  @GetMapping("/api/courses")
  public static List<Course> findAllCourses() {
    return courses;
  }


  @CrossOrigin(allowCredentials = "true")
  @GetMapping("/api/courses/{courseId}")
  public static Course findCourseById(
          @PathVariable("courseId") String courseId) {
      for(Course course : courses) {
        if(course.getId().equals(courseId)){
          return course;
        }
      }
      return null;
  }

  @CrossOrigin(allowCredentials = "true")
  @PostMapping("/api/courses")
  public Course createCourse(@RequestBody Course course) {
    courses.add(course);
    return course;
  }

  @PutMapping("/api/courses/{courseId}")
  public Course updateCourse(
          @PathVariable("courseId") String courseId,
          @RequestBody Course newCourse) {

    for (Course course : courses) {
      if (course.getId().equals(courseId)) {
        course.setTitle(newCourse.getTitle());
        return course;
      }
    }
    return null;
  }

  @CrossOrigin(allowCredentials = "true")
  @DeleteMapping("api/courses/{courseId}")
  public List<Course> deleteCourse(
          @PathVariable("courseId") String courseId) {

    for(Course course : courses) {
      if (course.getId().equals(courseId)) {
        courses.remove(course);
        return courses;
      }
    }
    return courses;
  }

}
