class Task {
    ID;

    constructor(name, description, deadline, priority, category, status) {
        this.name = name;
        this.description = description;
        this.deadline = this.setDeadline(deadline);
        this.priority = this.setPriority(priority);
        this.category = category;
        this.status = this.setStatus(status);
    }

    setID(ID) {
        this.ID = ID;
    }

    getID() {
        return this.ID;
    }

    setPriority(n) {
        if (n === undefined || n === null) {
            throw new ReferenceError("Priority cannot be null");
        }

        let p = Number.parseInt(n);
        if (p >= 1 && p <= 5) {
            return n;
        }

        throw new TypeError("Priority has to be an integer number of 1 to 5") ;
    }

    setDeadline(strDate) {
        let date = this.parseDate(strDate);
        
        if (isNaN(date.getTime())) {
            console.error("Invalid date");
            return "";
        } 

        return date;
    }

    parseDate(strDate) {
        try {
            const [datePart, timePart] = strDate.split(" ");
            const [day, month, year] = datePart.split("/").map(Number);
            const [hour, min] = timePart.split(":").map(Number)

            return new Date(year, month - 1, day, hour, min);
        } catch (e) {
            console.log("Error parsing the date")
            return "";
        }
    }

    getDeadline() {
        if (this.deadline === "") {
            console.error("No valid date provided")
            return "";
        }

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
        if (!n) {
            console.error("Status cannot be null");
        }

        n = n.toUpperCase();

        switch (n) {
            case "DOING":
                return "DOING";
            case "DONE":
                return "DONE";
            default:
                return "TODO";
        }
    }
}

class TaskService {

    currentID = 1;

    constructor(tasks) {
        this.tasks = tasks;
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
                `<td>${task.getID()}</td>` +
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

class Main {
    static main() {
        let taskList = [];
        let taskService = new TaskService(taskList);

        taskService.populateTasks();
        taskService.listTasks();

        document.getElementById("create-task-button").onclick = (e) => {
            e.preventDefault();
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
            
            taskService.createTask(task);
            taskService.listTasks();
        }

        document.getElementById("delete-task-button").onclick = (e) => {
            e.preventDefault();
            let taskID = document.getElementById("delete-by-id").value;
            
            taskService.deleteTaskByID(taskID);
            taskService.listTasks();
        }

        document.getElementById("sort-by").onchange = (e) => {
            e.preventDefault();
            let sortMethod = document.getElementById("sort-by").value;
            
            taskService.sortTasks(sortMethod);
            taskService.listTasks();
        }
    }

}

Main.main()




