package com.example.Assignment1.model;

import java.util.List;

public class Course {

  private String id;
  private String title;
  private List<Module> modules;

  public Course(String id, String title, List<Module> modules) {
    this.id = id;
    this.title = title;
    this.modules = modules;
  }

  public Course() {

  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
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
