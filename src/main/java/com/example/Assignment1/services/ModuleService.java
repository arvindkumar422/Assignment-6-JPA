package com.example.Assignment1.services;

import com.example.Assignment1.model.Module;
import com.example.Assignment1.repositories.CourseRepository;
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
public class ModuleService {

  List<Module> modules = new ArrayList<>();

  static CourseService courseservice = new CourseService();

//  {
//    for (Course course : courseservice.findAllCourses()) {
//      for (Module module : course.getModules()) {
//        modules.add(module);
//      }
//    }
//  }

  @Autowired
  ModuleRepository moduleRepo;
  @Autowired
  CourseRepository courseRepo;

  @PostMapping("/api/courses/{courseId}/modules")
  public Module createModule(@PathVariable("courseId") int courseId, @RequestBody Module module) {
//    Course course = courseRepo.findById(courseId).get();
//    course.getModules().add(module);
//    modules.add(module);
    module.setCourse(courseRepo.findById(courseId).get());
    return moduleRepo.save(module);
  }

  @CrossOrigin(allowCredentials = "true")
  @GetMapping("/api/courses/{courseId}/modules")
  public List<Module> findAllModules(@PathVariable("courseId") int courseId) {
//    Course course = courseRepo.findById(courseId).get();
//    return course.getModules();
    return moduleRepo.findModuleByCourseId(courseId);
  }

  @CrossOrigin(allowCredentials = "true")
  @GetMapping("/api/modules/{moduleId}")
  public Optional<Module> findModuleById(@PathVariable("moduleId") int moduleId) {
//    for (Course course : courseservice.findAllCourses()) {
//      for (Module module : course.getModules()) {
//        if (module.getId().equals(moduleId)) {
//          return module;
//        }
//      }
//    }
    return moduleRepo.findById(moduleId);
  }

  @PutMapping("/api/modules/{moduleId}")
  public Module updateModule(@PathVariable("moduleId") int moduleId, @RequestBody Module module) {
//    for (Course course : CourseService.findAllCourses()) {
//      for (Module mod : course.getModules()) {
//        if (mod.getId().equals(moduleId)) {
//          mod.setTitle(module.getTitle());
//          return mod;
//        }
//      }
//
//    }
    Module m = moduleRepo.findById(moduleId).get();
    m.setTitle(module.getTitle());
    return moduleRepo.save(m);
  }

  @CrossOrigin(allowCredentials = "true")
  @DeleteMapping("/api/modules/{moduleId}")
  public List<Module> deleteModule(@PathVariable("moduleId") int moduleId) {
//    for (Course course : CourseService.findAllCourses()) {
//      for (Module mod : course.getModules()) {
//        if (mod.getId().equals(moduleId)) {
//          modules.remove(mod);
//          course.getModules().remove(mod);
//          return modules;
//        }
//      }
//
//    }
    moduleRepo.deleteById(moduleId);
    return (List<Module>) moduleRepo.findAll();
  }

}
