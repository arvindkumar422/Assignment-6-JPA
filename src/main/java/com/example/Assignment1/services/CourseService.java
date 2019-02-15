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

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class CourseService {
  List<Course> courses = new ArrayList<>();
  List<Widget> widgets = new ArrayList<>();
  List<Topic> topics = new ArrayList<>();
  List<Lesson> lessons = new ArrayList<>();
  List<Module> modules = new ArrayList<>();
  {
    Widget widget = new Widget("1", "LIST","top_end");
    widgets.add(widget);
    Topic topic = new Topic("2", "topic1", widgets);
    topics.add(topic);
    Lesson lesson = new Lesson("2", "lesson1", topics);
    lessons.add(lesson);
    Module module = new Module("2", "module1", lessons);
    modules.add(module);
    Course course = new Course("2", "course1", modules);
    courses.add(course);
  }

  @GetMapping("/api/courses")
  public List findAllCourses() {
    return courses;
  }


  @GetMapping("/api/courses/{courseId}")
  public Course findCourseById(
          @PathVariable("courseId") String courseId) {
      for(Course course : courses) {
        if(course.getId().equals(courseId)){
          return course;
        }
      }
      return null;
  }

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

  @DeleteMapping("api/courses/{courseId}")
  public void deleteCourse(
          @PathVariable("courseId") String courseId) {

    for(Course course : courses) {
      if (course.getId().equals(courseId)) {
        courses.remove(course);
        return;
      }
    }
  }

}
