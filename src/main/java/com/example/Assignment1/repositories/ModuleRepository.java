package com.example.Assignment1.repositories;

import com.example.Assignment1.model.Module;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

  @Query("SELECT module FROM Module module WHERE module.course.id=:course_id")
  public List<Module> findModuleByCourseId (
          @Param("course_id") int course_id);


}
