package com.example.Assignment1.model;

import java.util.List;

public class Lesson {

  private String id;
  private String title;
  private List<Topic> topics;

  public Lesson(String id, String title, List<Topic> topics) {
    this.id = id;
    this.title = title;
    this.topics = topics;
  }

  public Lesson() {

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

  public List<Topic> getTopics() {
    return topics;
  }

  public void setTopics(List<Topic> topics) {
    this.topics = topics;
  }
}
