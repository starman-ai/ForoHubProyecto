package com.alura.forohub.service;


import com.alura.forohub.entity.Topic;
import com.alura.forohub.exception.ConflictException;
import com.alura.forohub.exception.ResourceNotFoundException;
import com.alura.forohub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Topic findById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    public Topic save(Topic topic) {
        if (topicRepository.findByTitleAndMessage(topic.getTitle(), topic.getMessage()).isPresent()) {
            throw new ConflictException("Topic with same title and message already exists");
        }
        return topicRepository.save(topic);
    }

    public Topic update(Long id, Topic topic) {
        Topic existingTopic = findById(id);
        existingTopic.setTitle(topic.getTitle());
        existingTopic.setMessage(topic.getMessage());
        existingTopic.setStatus(topic.getStatus());
        existingTopic.setAuthor(topic.getAuthor());
        existingTopic.setCourse(topic.getCourse());
        return topicRepository.save(existingTopic);
    }

    public void delete(Long id) {
        Topic topic = findById(id);
        topicRepository.delete(topic);
    }
}
