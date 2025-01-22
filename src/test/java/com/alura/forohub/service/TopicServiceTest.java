package com.alura.forohub.service;

import com.alura.forohub.entity.Topic;
import com.alura.forohub.exception.ConflictException;
import com.alura.forohub.exception.ResourceNotFoundException;
import com.alura.forohub.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicService topicService;

    private Topic topic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        topic = new Topic();
        topic.setId(1L);
        topic.setTitle("Title");
        topic.setMessage("Message");
        topic.setStatus("Status");
        topic.setAuthor("Author");
        topic.setCourse("Course");
    }

    @Test
    void testFindById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        Topic foundTopic = topicService.findById(1L);
        assertNotNull(foundTopic);
        assertEquals(topic.getTitle(), foundTopic.getTitle());
    }

    @Test
    void testFindByIdNotFound() {
        when(topicRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> topicService.findById(1L));
    }

    @Test
    void testSave() {
        when(topicRepository.findByTitleAndMessage(topic.getTitle(), topic.getMessage())).thenReturn(Optional.empty());
        when(topicRepository.save(topic)).thenReturn(topic);
        Topic savedTopic = topicService.save(topic);
        assertNotNull(savedTopic);
        assertEquals(topic.getTitle(), savedTopic.getTitle());
    }

    @Test
    void testSaveConflict() {
        when(topicRepository.findByTitleAndMessage(topic.getTitle(), topic.getMessage())).thenReturn(Optional.of(topic));
        assertThrows(ConflictException.class, () -> topicService.save(topic));
    }

    @Test
    void testUpdate() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(topicRepository.save(topic)).thenReturn(topic);
        Topic updatedTopic = topicService.update(1L, topic);
        assertNotNull(updatedTopic);
        assertEquals(topic.getTitle(), updatedTopic.getTitle());
    }

    @Test
    void testDelete() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        doNothing().when(topicRepository).delete(topic);
        topicService.delete(1L);
        verify(topicRepository, times(1)).delete(topic);
    }
}