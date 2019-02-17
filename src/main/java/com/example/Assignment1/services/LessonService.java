package com.example.Assignment1.services;

import com.example.Assignment1.model.Course;
import com.example.Assignment1.model.Lesson;
import com.example.Assignment1.model.Module;

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
@CrossOrigin(origins = "https://assignment5-neu.herokuapp.com")
public class LessonService {

  List<Lesson> lessons = new ArrayList<>();
  CourseService courseService = new CourseService();
  ModuleService moduleService = new ModuleService();

  @PostMapping("/api/modules/{moduleId}/lessons")
  public Lesson createLesson(@PathVariable("moduleId") String moduleId, @RequestBody Lesson lesson) {
    Module module = moduleService.findModuleById(moduleId);
    module.getLessons().add(lesson);
    lessons.add(lesson);
    return lesson;
  }

  @GetMapping("/api/modules/{moduleId}/lessons")
  public List<Lesson> findAllLessons(@PathVariable("moduleId") String moduleId) {
    Module module = ModuleService.findModuleById(moduleId);
    return module.getLessons();
  }

  @GetMapping("/api/lessons/{lessonId}")
  public static Lesson findLessonById(@PathVariable("lessonId") String lessonId) {
    for(Course course : CourseService.findAllCourses()) {
      for(Module module : course.getModules()) {
        for(Lesson lesson : module.getLessons()) {
          if (lesson.getId().equals(lessonId)) {
            return lesson;
          }
        }
      }
    }
    return null;
  }

  @PutMapping("/api/lessons/{lessonId}")
  public Lesson updateLesson(@PathVariable("lessonId") String lessonId, @RequestBody Lesson lesson) {
    for(Course course : CourseService.findAllCourses()) {
      for(Module module : course.getModules()) {
        for(Lesson less : module.getLessons()) {
          if (less.getId().equals(lessonId)) {
            less.setTitle(lesson.getTitle());
            return lesson;
          }
        }
      }
    }
    return null;
  }

  @DeleteMapping("/api/lessons/{lessonId}")
  public void deleteLesson(@PathVariable("lessonId") String lessonId) {
    for(Course course : CourseService.findAllCourses()) {
      for(Module module : course.getModules()) {
        for(Lesson less : module.getLessons()) {
          if (less.getId().equals(lessonId)) {
            lessons.remove(less);
            module.getLessons().remove(less);
            return;
          }
        }
      }
    }
    return;
  }
}
