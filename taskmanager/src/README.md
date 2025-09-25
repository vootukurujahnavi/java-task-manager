# Task 1 - Java Task Manager REST API
**Author:** Vootukuru Jahnavi
**Date:** 24-09-2025

## Overview 
This project implements a Java backend with a REST API for managing "task" objects.
Each task represents a shell command and can be executed ,storing execution details in MongoDB.
The API supports creating,deleting,searching, and running tasks.

## API Endpoints
### GET/tasks
returns all tasks
Tested using Postman

### GET /task?id={id}
Returns a single task by ID . or If Not found Returns 404 
### PUT/tasks
creates or updates a task . Body is Json. Example:
{
"id":"124",
"name":"Print Hello",
"owner":"Vootukuru Jahnavi",
"command":"echo Hello World"
}

### DELETE/tasks/{id}
Delete a task by ID.

### GET/tasks/find?name={string}
searches tasks by name substring

### POST /tasks/{id}/execute
Executes the task command and stores the output in taskExecutions list.



