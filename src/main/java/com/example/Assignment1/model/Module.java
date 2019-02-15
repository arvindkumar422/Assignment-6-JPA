package com.example.Assignment1.model;

import java.util.List;

public class Module {

  private String id;
  private String title;
  private List<Lesson> lessons;

  public Module(String id, String title, List<Lesson> lessons) {
    this.id = id;
    this.title = title;
    this.lessons = lessons;
  }

  public Module() {

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

  public List<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
  }
}
