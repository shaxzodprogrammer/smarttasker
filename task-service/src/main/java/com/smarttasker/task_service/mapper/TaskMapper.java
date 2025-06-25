package com.smarttasker.task_service.mapper;

import com.smarttasker.task_service.dto.TaskRequest;
import com.smarttasker.task_service.dto.TaskResponse;
import com.smarttasker.task_service.entity.Task;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
public class TaskMapper {
    public static TaskResponse toDto(Task task) {
        TaskResponse dto = new TaskResponse();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDeadline(LocalDate.from(task.getDeadline()));
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setCreatedBy(task.getCreatedBy().toString());
        return dto;
    }

    public static Task fromDto(TaskRequest dto, String createdBy) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDeadline(dto.getDeadline().atStartOfDay());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setCreatedBy(createdBy);
        return task;
    }
}
