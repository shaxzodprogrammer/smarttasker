package com.smarttasker.task_service.repository;

import com.smarttasker.task_service.entity.Task;
import com.smarttasker.task_service.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByCreatedBy(String createdBy);

    List<Task> findByStatus(TaskStatus status);
}