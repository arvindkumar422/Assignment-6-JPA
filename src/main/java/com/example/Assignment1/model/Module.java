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
public class Module {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  @OneToMany(mappedBy = "module")
  private List<Lesson> lessons;

  @ManyToOne
  @JsonIgnore
  private Course course;

  public Module(int id, String title, List<Lesson> lessons) {
    this.id = id;
    this.title = title;
    this.lessons = lessons;
  }

  public Module() {

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

  public List<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
    if (!course.getModules().contains(this)){
      course.getModules().add(this);
    }
  }
}
