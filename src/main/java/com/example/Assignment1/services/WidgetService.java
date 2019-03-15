package com.example.Assignment1.services;

import com.example.Assignment1.model.Module;
import com.example.Assignment1.model.Widget;
import com.example.Assignment1.repositories.LessonRepository;
import com.example.Assignment1.repositories.TopicRepository;
import com.example.Assignment1.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://assignment-6-neu.herokuapp.com")
public class WidgetService {

  @Autowired
  WidgetRepository widgetRepo;
  @Autowired
  TopicRepository topicRepo;

  @CrossOrigin(allowCredentials = "true")
  @GetMapping("/api/widget/{widgetId}")
  public Optional<Widget> findWidgetById(@PathVariable("widgetId") int widgetId) {
    return widgetRepo.findById(widgetId);
  }

  @CrossOrigin(allowCredentials = "true")
  @PutMapping("/api/widget/{widgetId}")
  public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget widget) {
    Widget w = widgetRepo.findById(widgetId).get();
    w.setType(widget.getType());
    return widgetRepo.save(w);
  }

  @CrossOrigin(allowCredentials = "true")
  @DeleteMapping("/api/widget/{widgetId}")
  public void deleteWidget(@PathVariable("widgetId") int widgetId) {
    widgetRepo.deleteById(widgetId);
  }

}
