package com.smarttasker.task_service.controller;

import com.smarttasker.task_service.dto.TaskRequest;
import com.smarttasker.task_service.dto.TaskResponse;
import com.smarttasker.task_service.entity.Task;
import com.smarttasker.task_service.mapper.TaskMapper;
import com.smarttasker.task_service.model.TaskStatus;
import com.smarttasker.task_service.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Powered by: Shakhzod Akhmedov
 * 6/23/2025
 */
@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskRequest request,
                                               Authentication auth) {
        Task task = TaskMapper.fromDto(request, auth.getName());
        return ResponseEntity.ok(TaskMapper.toDto(taskService.create(task)));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAll() {
        return ResponseEntity.ok(taskService.getAll().stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable UUID  id) {
        return ResponseEntity.ok(TaskMapper.toDto(taskService.getById(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @taskSecurity.isOwner(#id, authentication.name)")
    public ResponseEntity<TaskResponse> update(@PathVariable UUID  id,
                                               @RequestBody @Valid TaskRequest request) {
        Task updated = TaskMapper.fromDto(request, "dummy");
        return ResponseEntity.ok(TaskMapper.toDto(taskService.update(id, updated)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @taskSecurity.isOwner(#id, authentication.name)")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        System.out.println("id yopta" + id);
        taskService.delete(id);
        return ResponseEntity.ok("Task was deleted succesfully");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TaskResponse>> getByUser(Authentication auth) {
        return ResponseEntity.ok(taskService.getByUser(auth.getName()).stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.getByStatus(status).stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList()));
    }
}
