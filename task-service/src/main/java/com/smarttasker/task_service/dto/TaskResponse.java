package com.smarttasker.task_service.dto;

import com.smarttasker.task_service.model.TaskPriority;
import com.smarttasker.task_service.model.TaskStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
@Data
public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    private LocalDate deadline;
    private TaskPriority priority;
    private TaskStatus status;
    private String createdBy;
}
