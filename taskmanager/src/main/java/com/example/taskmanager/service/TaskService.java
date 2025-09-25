package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskExecution;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskExecution runTask(Task task) {
        TaskExecution execution = new TaskExecution();
        execution.setTaskId(task.getId());
        execution.setStartTime(new Date());

        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder builder;

            if (os.contains("win")) {
                builder = new ProcessBuilder("cmd.exe", "/c", task.getCommand());
            } else {
                builder = new ProcessBuilder("/bin/bash", "-c", task.getCommand());
            }

            builder.redirectErrorStream(true);
            Process process = builder.start();

            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            execution.setOutput(output.toString());
            execution.setEndTime(new Date());
            execution.setStatus("SUCCESS");

        } catch (Exception e) {
            execution.setOutput(e.getMessage());
            execution.setEndTime(new Date());
            execution.setStatus("FAILED");
        }

        return execution;
    }
}
