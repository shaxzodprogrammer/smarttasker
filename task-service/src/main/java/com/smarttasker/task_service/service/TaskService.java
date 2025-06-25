package com.smarttasker.task_service.service;

import com.smarttasker.task_service.entity.Task;
import com.smarttasker.task_service.model.TaskStatus;
import com.smarttasker.task_service.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task getById(UUID  id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task update(UUID id, Task updatedTask) {
        Task existing = getById(id);
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setDeadline(updatedTask.getDeadline());
        existing.setPriority(updatedTask.getPriority());
        existing.setStatus(updatedTask.getStatus());
        return taskRepository.save(existing);
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getByUser(String createdBy) {
        return taskRepository.findByCreatedBy(createdBy);
    }

    public List<Task> getByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}