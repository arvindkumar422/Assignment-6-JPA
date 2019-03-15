package com.example.Assignment1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Widget {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String type;
  private String position;

  @ManyToOne
  @JsonIgnore
  private Topic topic;

  public Widget(int id, String type, String position) {
    this.id = id;
    this.type = type;
    this.position = position;
  }

  public Widget() {

  }



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }


  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }
}
