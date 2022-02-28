package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Exc 29.2
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Content 1");

        //When
        Task resultTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(resultTask.getId(), 1L);
        assertEquals(resultTask.getTitle(), "Task 1");
        assertEquals(resultTask.getContent(), "Content 1");
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Task 1", "Content 1");

        //When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(resultTaskDto.getId(), 1L);
        assertEquals(resultTaskDto.getTitle(), "Task 1");
        assertEquals(resultTaskDto.getContent(), "Content 1");
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Content 1");
        Task task2 = new Task(2L, "Task 2", "Content 2");
        Task task3 = new Task(3L, "Task 3", "Content 3");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        //When
        List<TaskDto> resultTasksDto = taskMapper.mapToTaskDtoList(tasks);

        //Then
        TaskDto resultTaskDto1 = resultTasksDto.get(0);
        TaskDto resultTaskDto2 = resultTasksDto.get(1);
        TaskDto resultTaskDto3 = resultTasksDto.get(2);

        assertEquals(resultTasksDto.size(), 3);

        assertEquals(resultTaskDto1.getId(), 1L);
        assertEquals(resultTaskDto1.getTitle(), "Task 1");
        assertEquals(resultTaskDto1.getContent(), "Content 1");

        assertEquals(resultTaskDto2.getId(), 2L);
        assertEquals(resultTaskDto2.getTitle(), "Task 2");
        assertEquals(resultTaskDto2.getContent(), "Content 2");

        assertEquals(resultTaskDto3.getId(), 3L);
        assertEquals(resultTaskDto3.getTitle(), "Task 3");
        assertEquals(resultTaskDto3.getContent(), "Content 3");
    }
}
