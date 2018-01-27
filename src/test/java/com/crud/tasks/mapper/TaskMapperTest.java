package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest()  {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test_title","test_body");

        //When
        Task task =  taskMapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertEquals("test_title", task.getTitle());

    }

    @Test
    public void mapToTaskDtoTest()  {
        //Given
        Task task = new Task(1L,"test_title","test_body");

        //When
        TaskDto taskDto =  taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(task);
        assertEquals("test_body", task.getContent());

    }

    @Test
    public void mapToTaskDtoListTest()  {
        //Given
        Task task1 = new Task(1L,"test_title1","test_body1");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);

        //When
        List<TaskDto> taskDtoLists =  taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(taskDtoLists);
        assertEquals(1, taskDtoLists.size());

        taskDtoLists.forEach(taskDtoList -> {
            assertEquals("test_title1",taskDtoList.getTitle());
            assertEquals("test_body1",taskDtoList.getContent());
        });

    }

}
