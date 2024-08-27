class TaskService {

    currentID = 1;

    constructor(tasks) {
        if (localStorage.getItem("my-tasks")) {
            this.tasks = this.parseLocalStorage();
            this.currentID = this.tasks[this.tasks.length - 1].ID + 1;
        } else {
            this.tasks = tasks;
            this.populateTasks();
        }
    }

    parseLocalStorage() {
        let tempArray = JSON.parse(localStorage.getItem("my-tasks"));
        let finalArray = []
        for (let o of tempArray) {
            let task = new Task(
                o.name,
                o.description,
                o.deadline,
                o.priority,
                o.category,
                o.status,
                o.ID
            )

            finalArray.push(task);
        }

        return finalArray;
    }

    populateTasks() {
        let task = new Task(
            "Limpeza da casa",
            "A casa precisa ser limpa",
            "26/08/2024 08:30",
            2,
            "casa",
            "DONE"
        );

        this.createTask(task);

        let task2 = new Task(
            "Futebol",
            "jogo de futebol na próxima terça",
            "29/08/2024 20:40",
            1,
            "esporte",
            "TODO"
        );

        this.createTask(task2);
    }

    createTask(task) {
        task.setID(this.currentID);
        this.tasks.push(task);
        this.currentID++;
    }

    updateTaskByID(ID, changedTask) {
        let taskID = Number.parseInt(ID);

        for (let task of this.tasks) {
            if (task.ID === taskID) {
                task.name = changedTask.name;
                task.description = changedTask.description;
                task.deadline = changedTask.deadline;
                task.priority = changedTask.priority;
                task.category = changedTask.category;
                task.status = changedTask.status;

                return;
            }
        }
    }

    updateTaskStatusByID(ID, newStatus) {
        
        for (let task of this.tasks) {
            if (task.ID == ID) {
                task.setStatus(newStatus);
            }
        }
    }

    listTasks() {
        const COLORTYPE1 = "table-color1";
        const COLORTYPE2 = "table-color2";
        const PRIORITYALIGNMENT = "priority-alignment";

        let table = document.querySelector(".list-table-body");
        table.innerHTML = "";

        let i = 0;
        let colorSet = "";
        for (let task of this.tasks) {

            if (i % 2 === 0) {
                colorSet = COLORTYPE1;
            } else {
                colorSet = COLORTYPE2;
            }

            table.innerHTML +=
                `<tr class="${colorSet}">` +
                `<td><input type="checkbox" class="checkbox-tasks" value="${task.getID()}">${task.getID()}</td>` +
                `<td>${task.name}</td>` +
                `<td>${task.description}</td>` +
                `<td>${task.getDeadline()}</td>` +
                `<td class="${PRIORITYALIGNMENT}">${task.priority}</td>` +
                `<td>${task.category}</td>` +
                `<td>${task.status}</td>` +
                `</tr>`;
            i++;
        }

        this.saveAll();
    }

    saveAll() {
        localStorage.setItem("my-tasks", JSON.stringify(this.tasks));
    }

    deleteTaskByID(ID) {
        try {
            let taskID = Number.parseInt(ID)
            this.tasks = this.tasks.filter(task => task.ID !== taskID);
        } catch (e) {
            console.error("Invalid input: " + e.getmessage())
        }
    }

    sortTasks(sortType) {
        if (typeof sortType !== "string") {
            return;
        }

        sortType = sortType.toUpperCase();
        switch (sortType) {
            case "CATEGORY":
                this.tasks.sort((a, b) => a.category.localeCompare(b.category));
                break;
            case "STATUS":
                this.tasks.sort((a, b) => b.status.localeCompare(a.status));
                break;
            case "PRIORITY":
                this.tasks.sort((a, b) => a.priority - b.priority);
        }
    }
}