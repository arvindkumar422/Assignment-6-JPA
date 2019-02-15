package com.example.Assignment1.model;

import java.util.List;

public class Topic {


  private String id;
  private String title;
  private List<Widget> widgets;

  public Topic(String id, String title, List<Widget> widgets) {
    this.id = id;
    this.title = title;
    this.widgets = widgets;
  }

  public Topic() {

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

  public List<Widget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<Widget> widgets) {
    this.widgets = widgets;
  }
}
