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
public class Topic {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  @OneToMany(mappedBy = "topic")
  private List<Widget> widgets;

  @ManyToOne
  @JsonIgnore
  private Lesson lesson;

  public Topic(int id, String title, List<Widget> widgets) {
    this.id = id;
    this.title = title;
    this.widgets = widgets;
  }

  public Topic() {

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

  public List<Widget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<Widget> widgets) {
    this.widgets = widgets;
  }

  public Lesson getLesson() {
    return lesson;
  }

  public void setLesson(Lesson lesson) {
    this.lesson = lesson;
  }
}
