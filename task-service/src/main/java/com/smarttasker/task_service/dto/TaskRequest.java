package com.smarttasker.task_service.dto;

import com.smarttasker.task_service.model.TaskPriority;
import com.smarttasker.task_service.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
@Data
public class TaskRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDate deadline;

    @NotNull
    private TaskPriority priority;

    @NotNull
    private TaskStatus status;
}