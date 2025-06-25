package com.smarttasker.task_service.security;

import com.smarttasker.task_service.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
@Component("taskSecurity")
@RequiredArgsConstructor
public class TaskSecurity {
    private final TaskRepository taskRepository;

    public boolean isOwner(UUID taskId, String userId) {
        return taskRepository.findById(taskId)
                .map(task -> task.getCreatedBy().toString().equals(userId))
                .orElse(false);
    }
}
