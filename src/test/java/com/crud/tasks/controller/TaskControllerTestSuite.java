package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//Exc 29.2
@ExtendWith(MockitoExtension.class)
public class TaskControllerTestSuite {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService service;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void shouldCreateTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title 1", "Content 1");
        Task task = new Task(1L, "Title 1", "Content 1");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        //When
        ResponseEntity<Void> resultResponse = taskController.createTask(taskDto);

        //Then
        assertEquals(resultResponse, ResponseEntity.ok().build());
    }

    @Test
    public void shouldUpdateTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title 1", "Content 1");
        Task task = new Task(1L, "Title 1", "Content 1");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        //When
        ResponseEntity<TaskDto> resultTaskDto = taskController.updateTask(taskDto);

        //Then
        assertThat(resultTaskDto).isNotNull();
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        Task task = new Task(1L, "Title 1", "Content 1");

        when(service.ifExist(task.getId())).thenReturn(true);

        //When
        ResponseEntity<Void> resultResponse = taskController.deleteTask(task.getId());

        //Then
        assertEquals(resultResponse, ResponseEntity.ok().build());
    }

    @Test
    public void shouldFetchTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Title 1", "Content 1");
        TaskDto taskDto = new TaskDto(1L, "Title 1", "Content 1");

        when(service.findTask(task.getId())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        ResponseEntity<TaskDto> resultTaskDto = taskController.getTask(task.getId());

        //Then
        assertThat(resultTaskDto).isNotNull();
    }

    @Test
    public void shouldFetchTasks() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Title 1", "Content 1");
        Task task2 = new Task(2L, "Title 2", "Content 2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);

        TaskDto taskDto = new TaskDto(1L, "Title 1", "Content 1");
        TaskDto taskDto2 = new TaskDto(2L, "Title 2", "Content 2");
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(taskDto);
        tasksDto.add(taskDto2);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //When
        ResponseEntity<List<TaskDto>> resultTasksDto = taskController.getTasks();

        //Then
        assertThat(resultTasksDto).isNotNull();
    }
}
