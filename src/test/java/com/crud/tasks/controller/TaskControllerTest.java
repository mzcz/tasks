package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTaskEmptyDtoList() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //orIsOk
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldMapToTaskDtoList() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1L, "My Test1","My Test Description1"));
        taskDtos.add(new TaskDto(2L, "My Test2","My Test Description2"));

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("My Test1")))
                .andExpect(jsonPath("$[0].content", is("My Test Description1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("My Test2")))
                .andExpect(jsonPath("$[1].content", is("My Test Description2")));
    }
    @Ignore
    @Test
    public void shouldMapToTaskDto() throws Exception {
        //Given

        when(taskMapper.mapToTaskDto(service.getTask(1L).get()))
                .thenReturn(new TaskDto(1L, "My Test1","My Test Description1"));


        //When & Then

            mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].title", is("My Test1")))
                    .andExpect(jsonPath("$[0].content", is("My Test Description1")));

    }
    @Ignore
    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task();

        //when(service.delete(task))
               // .thenReturn(new TaskDto());

        TaskDto taskDto = new TaskDto(1L, "My Test1","My Test Description1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDtoBefore = new TaskDto(1L, "My Test1","My Test Description1");

        TaskDto taskDtoAfter = new TaskDto(1L, "My Test2","My Test Description2");

        when(taskMapper.mapToTaskDto(service.save(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class)))))
                .thenReturn(taskDtoAfter);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDtoBefore);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("My Test2")))
                .andExpect(jsonPath("$.content", is("My Test Description2")));
    }

    @Ignore
    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "My Test1","My Test Description1");
        Task task = taskMapper.mapToTask(taskDto);

        when(service.save(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))))
                .thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("My Test1")))
                .andExpect(jsonPath("$.content", is("My Test Description1")));
    }

}