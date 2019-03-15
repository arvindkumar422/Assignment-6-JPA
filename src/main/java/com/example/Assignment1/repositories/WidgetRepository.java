package com.example.Assignment1.repositories;

import com.example.Assignment1.model.Topic;
import com.example.Assignment1.model.Widget;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
  @Query("SELECT widget FROM Widget widget WHERE widget.topic.id=:topic_id")
  public List<Widget> findWidgetByTopic (
          @Param("topic_id") int topic_id);
}
