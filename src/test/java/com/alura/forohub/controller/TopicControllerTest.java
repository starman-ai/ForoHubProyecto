package com.alura.forohub.controller;

import com.alura.forohub.entity.Topic;
import com.alura.forohub.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TopicController.class)
class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TopicService topicService;

    @InjectMocks
    private TopicController topicController;

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
    void testGetAllTopics() throws Exception {
        when(topicService.findAll()).thenReturn(Collections.singletonList(topic));
        mockMvc.perform(get("/topics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(topic.getTitle()));
    }

    @Test
    void testGetTopicById() throws Exception {
        when(topicService.findById(1L)).thenReturn(topic);
        mockMvc.perform(get("/topics/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(topic.getTitle()));
    }

    @Test
    void testCreateTopic() throws Exception {
        when(topicService.save(any(Topic.class))).thenReturn(topic);
        mockMvc.perform(post("/topics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Title\",\"message\":\"Message\",\"status\":\"Status\",\"author\":\"Author\",\"course\":\"Course\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(topic.getTitle()));
    }

    @Test
    void testUpdateTopic() throws Exception {
        when(topicService.update(eq(1L), any(Topic.class))).thenReturn(topic);
        mockMvc.perform(put("/topics/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"message\":\"Updated Message\",\"status\":\"Updated Status\",\"author\":\"Updated Author\",\"course\":\"Updated Course\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(topic.getTitle()));
    }

    @Test
    void testDeleteTopic() throws Exception {
        doNothing().when(topicService).delete(1L);
        mockMvc.perform(delete("/topics/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}