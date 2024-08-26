class Task {
    
    constructor(name, description, deadline, priority, category, status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = this.setPriority(priority);
        this.category = category;
        this.status = status;
    }

    setPriority(n) {
        if (n === undefined || n === null) {
            throw new ReferenceError("Priority cannot be null");
        }

        if (Number.isInteger(n) && n >= 1 && n <= 5) {
            return n;
        }

        throw new TypeError("Priority has to be an integer number of 1 to 5") ;
    }

    setDeadline(strDate) {
        return new Date(strDate);
    }

    getDeadline() {
        return this.formatDate(this.deadline.getDate()) +
               "/" +
               this.formatDate(this.deadline.getMonth() + 1) +
               "/" +
               this.deadline.getFullYear().toString();
    }

    formatDate(n) {
        if (n < 10) {
            return "0" + n;
        }

        return n;
    }

    setStatus(n) {
        if (n === undefined || n === null) {
            throw new ReferenceError("Status cannot be null");
        }

        if (typeof n !== "string") {
            throw new TypeError("Status has to be a string");
        }

        n = n.toUpperCase();

        switch (n) {
            case "TODO":
                return "TODO";
            case "DOING":
                return "DOING";
            case "DONE":
                return "DONE";
            default:
                throw new RangeError("Status can only be set as TODO, DOING or DONE");
        }
    }
}

class TaskService {

    constructor(tasks) {
        this.tasks = tasks;
    }

    populateTasks() {
        let task = new Task(
            "Limpeza da casa",
            "A casa precisa ser limpa",
            new Date(),
            2,
            "casa",
            "TODO"
        );

        this.tasks.push(task);

        let task2 = new Task(
            "Futebol",
            "jogo de futebol na próxima terça",
            new Date(2024, 7, 27),
            1,
            "esporte",
            "TODO"
        );

        this.tasks.push(task2);
    }

    createTask() {

    }

    listTasks() {
        const COLORTYPE1 = "table-color1";
        const COLORTYPE2 = "table-color2";
        const PRIORITYALIGNMENT = "priority-alignment";

        let table = document.querySelector(".list-table-body");
        table.innerHTML += "<tr></tr>";
        
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
                `<td>${task.name}</td>` +
                `<td>${task.description}</td>` +
                `<td>${task.getDeadline()}</td>` +
                `<td class="${PRIORITYALIGNMENT}">${task.priority}</td>` +
                `<td>${task.category}</td>` +
                `<td>${task.status}</td>` +
            `</tr>`;
            i++;
        }
    }
}

class Main {
    static main() {
        let taskList = [];
        let taskService = new TaskService(taskList);

        taskService.populateTasks();
        taskService.listTasks();
    }
}

Main.main()

