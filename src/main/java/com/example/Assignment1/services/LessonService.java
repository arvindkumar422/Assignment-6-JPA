package com.example.Assignment1.services;

import com.example.Assignment1.model.Lesson;
import com.example.Assignment1.repositories.LessonRepository;
import com.example.Assignment1.repositories.ModuleRepository;

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
@CrossOrigin(origins = "https://angular-neu-7.herokuapp.com")
public class LessonService {

  List<Lesson> lessons = new ArrayList<>();

  @Autowired
  LessonRepository lessonRepo;
  @Autowired
  ModuleRepository moduleRepo;

  @PostMapping("/api/modules/{moduleId}/lessons")
  public Lesson createLesson(@PathVariable("moduleId") int moduleId, @RequestBody Lesson lesson) {
//    Module module = moduleRepo.findById(moduleId).get();
//    module.getLessons().add(lesson);
//    lessons.add(lesson);
    lesson.setModule(moduleRepo.findById(moduleId).get());
    return lessonRepo.save(lesson);
  }

  @GetMapping("/api/modules/{moduleId}/lessons")
  public List<Lesson> findAllLessons(@PathVariable("moduleId") int moduleId) {
//    Module module = moduleRepo.findById(moduleId).get();
//    return module.getLessons();
    return lessonRepo.findLessonByModule(moduleId);
  }

  @GetMapping("/api/lessons/{lessonId}")
  public Optional<Lesson> findLessonById(@PathVariable("lessonId") int lessonId) {
//    for(Course course : CourseService.findAllCourses()) {
//      for(Module module : course.getModules()) {
//        for(Lesson lesson : module.getLessons()) {
//          if (lesson.getId().equals(lessonId)) {
//            return lesson;
//          }
//        }
//      }
//    }
    return lessonRepo.findById(lessonId);
  }

  @PutMapping("/api/lessons/{lessonId}")
  public Lesson updateLesson(@PathVariable("lessonId") int lessonId, @RequestBody Lesson lesson) {
//    for(Course course : CourseService.findAllCourses()) {
//      for(Module module : course.getModules()) {
//        for(Lesson less : module.getLessons()) {
//          if (less.getId().equals(lessonId)) {
//            less.setTitle(lesson.getTitle());
//            return lesson;
//          }
//        }
//      }
//    }
    Lesson l = lessonRepo.findById(lessonId).get();
    l.setTitle(lesson.getTitle());
    return lessonRepo.save(l);
  }

  @DeleteMapping("/api/lessons/{lessonId}")
  public void deleteLesson(@PathVariable("lessonId") int lessonId) {
//    for(Course course : CourseService.findAllCourses()) {
//      for(Module module : course.getModules()) {
//        for(Lesson less : module.getLessons()) {
//          if (less.getId().equals(lessonId)) {
//            lessons.remove(less);
//            module.getLessons().remove(less);
//            return;
//          }
//        }
//      }
//    }
    lessonRepo.deleteById(lessonId);
  }
}
