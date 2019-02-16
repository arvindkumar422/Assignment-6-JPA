package com.example.Assignment1.services;

import com.example.Assignment1.model.Course;
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
@CrossOrigin(origins = "http://assignment5-neu.herokuapp.com")
public class ModuleService {

  List<Module> modules = new ArrayList<>();

  static CourseService courseservice = new CourseService();
  {
    for (Course course : courseservice.findAllCourses()) {
      for(Module module : course.getModules()) {
        modules.add(module);      }
    }
  }

  @PostMapping("/api/courses/{courseId}/modules")
  public Module createModule(@PathVariable("courseId") String courseId, @RequestBody Module module) {
    Course course = courseservice.findCourseById(courseId);
    course.getModules().add(module);
    modules.add(module);
    return module;
  }

  @CrossOrigin(allowCredentials = "true")
  @GetMapping("/api/courses/{courseId}/modules")
  public List<Module> findAllModules(@PathVariable("courseId") String courseId) {
    Course course = CourseService.findCourseById(courseId);
    return course.getModules();
  }

  @CrossOrigin(allowCredentials = "true")
  @GetMapping("/api/modules/{moduleId}")
  public static Module findModuleById(@PathVariable("moduleId") String moduleId) {
    for (Course course : courseservice.findAllCourses()) {
      for (Module module : course.getModules()) {
        if (module.getId().equals(moduleId)) {
          return module;
        }
      }
    }
    return null;
  }

  @PutMapping("/api/modules/{moduleId}")
  public Module updateModule(@PathVariable("moduleId") String moduleId, @RequestBody Module module) {
    for (Module mod : modules) {
      if (mod.getId().equals(moduleId)) {
        mod.setTitle(module.getTitle());
        return mod;
      }
    }
    return null;
  }

  @CrossOrigin(allowCredentials = "true")
  @DeleteMapping("/api/modules/{moduleId}")
  public List<Module> deleteModule(@PathVariable("moduleId") String moduleId) {
    for (Module module : modules) {
      if (module.getId().equals(moduleId)) {
        modules.remove(module);
        for (Course course : courseservice.courses) {
          if (course.getModules().contains(module)) {
            course.getModules().remove(module);
            return modules;
          }
        }

      }
    }
    return modules;
  }

}
