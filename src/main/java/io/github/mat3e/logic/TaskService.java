package io.github.mat3e.logic;

import io.github.mat3e.model.Task;
import io.github.mat3e.model.TaskRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {
    private final TaskRepository repository;

    TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Async
    public CompletableFuture<List<Task>> findAllAsync() {
        return CompletableFuture.supplyAsync(repository::findAll);
    }
}
