package com.smarttasker.task_service.dto;

import com.smarttasker.task_service.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
@Data
public class TaskDto {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Deadline is required")
    private LocalDateTime deadline;

    private TaskStatus status;
}
