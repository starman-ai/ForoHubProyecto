package com.alura.forohub.controller;

import com.alura.forohub.entity.Topic;
import com.alura.forohub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@Validated
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<Topic> create(@Valid @RequestBody Topic topic) {
        Topic savedTopic = topicService.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTopic);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Topic> topics = topicService.findAll();
        if (topics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("It's empty here, for now");
        }
        return ResponseEntity.status(HttpStatus.OK).body(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getById(@PathVariable Long id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> update(@PathVariable Long id, @Valid @RequestBody Topic topic) {
        Topic updatedTopic = topicService.update(id, topic);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}