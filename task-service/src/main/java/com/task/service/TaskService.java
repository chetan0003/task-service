package com.task.service;

import com.task.model.Response;
import com.task.model.Task;

public interface TaskService {
    Response getCalculateEndDate(Task task);
}
