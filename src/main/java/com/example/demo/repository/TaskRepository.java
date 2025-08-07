package com.example.demo.repository;

import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task.Status status);

    List<Task> findByPriority(Task.Priority priority);

    List<Task> findByStatusAndPriority(Task.Status status, Task.Priority priority);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = :status")
    long countByStatus(Task.Status status);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.priority = :priority")
    long countByPriority(Task.Priority priority);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.dueDate < :date AND t.status = 'PENDING'")
    long countOverdueTasks(LocalDate date);

    List<Task> findByDueDateBetween(LocalDate startDate, LocalDate endDate);

    List<Task> findByTitleContainingIgnoreCase(String title);
}
