let taskList = [];
let taskService = new TaskService(taskList);

taskService.listTasks();

document.getElementById("create-task-button").onclick = (e) => {
    e.preventDefault();
    let taskID = document.getElementById("insert-id").value;
    let taskName = document.getElementById("insert-name").value;
    let taskDescription = document.getElementById("insert-description").value;
    let taskDeadLine = document.getElementById("insert-deadline").value;
    let taskPriority = document.getElementById("insert-priority").value;
    let taskCategory = document.getElementById("insert-category").value;
    let taskStatus = document.getElementById("insert-status").value;

    let task = new Task(
        taskName,
        taskDescription,
        taskDeadLine,
        taskPriority,
        taskCategory,
        taskStatus
    )

    if (taskID) {
        taskService.updateTaskByID(taskID, task);
    } else {
        taskService.createTask(task);
    }

    taskService.listTasks();
}

document.getElementById("sort-by").onchange = (e) => {
    e.preventDefault();
    let sortType = document.getElementById("sort-by").value;

    taskService.sortTasks(sortType);
    taskService.listTasks();
    e.target.selectedIndex = 0;
}

document.getElementById("update-status").onchange = (e) => {
    let taskIDs = document.getElementsByClassName("checkbox-tasks");

    for (let checkbox of taskIDs) {
        if (checkbox.checked) {
            let updatedStatus = document.getElementById("update-status").value;
            taskService.updateTaskStatusByID(checkbox.value, updatedStatus);
        }
    }

    taskService.listTasks();
    e.target.selectedIndex = 0;
}

document.getElementById("delete-task-button").onclick = (e) => {
    e.preventDefault();
    let taskIDs = document.getElementsByClassName("checkbox-tasks");

    for (let checkbox of taskIDs) {
        if (checkbox.checked) {
            taskService.deleteTaskByID(checkbox.value);
        }
    }

    taskService.listTasks();
}