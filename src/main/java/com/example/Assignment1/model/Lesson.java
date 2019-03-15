package com.example.Assignment1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lesson {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  @OneToMany(mappedBy = "lesson")
  private List<Topic> topics;

  @ManyToOne
  @JsonIgnore
  private Module module;

  public Lesson(int id, String title, List<Topic> topics) {
    this.id = id;
    this.title = title;
    this.topics = topics;
  }

  public Lesson() {

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

  public List<Topic> getTopics() {
    return topics;
  }

  public void setTopics(List<Topic> topics) {
    this.topics = topics;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }
}
