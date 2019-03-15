package com.example.Assignment1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  @OneToMany(mappedBy = "course")
  private List<Module> modules;

  public void module(Module module) {
    this.modules.add(module);
    if (module.getCourse() != this) {
      module.setCourse(this);
    }
  }




  public Course(int id, String title, List<Module> modules) {
    this.id = id;
    this.title = title;
    this.modules = modules;
  }

  public Course() {

  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Module> getModules() {
    return modules;
  }

  public void setModules(List<Module> modules) {
    this.modules = modules;
  }

}
