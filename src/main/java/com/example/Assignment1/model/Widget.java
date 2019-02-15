package com.example.Assignment1.model;

public class Widget {

  private String id;
  private String type;
  private String position;

  public Widget(String id, String type, String position) {
    this.id = id;
    this.type = type;
    this.position = position;
  }

  public Widget() {

  }



  public String getId() {
    return id;
  }

  public void setId(String id) {
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



}
