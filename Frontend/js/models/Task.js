class Task {

    constructor(name, description, deadline, priority, category, status, ID = 0) {
        this.name = name;
        this.description = description;
        this.setDeadline(deadline);
        this.setPriority(priority);
        this.category = category;
        this.setStatus(status);
        this.ID = ID;
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
            this.priority = n;
            return;
        }

        throw new TypeError("Priority has to be an integer number of 1 to 5");
    }

    setDeadline(strDate) {
        if (!isNaN(Date.parse(strDate))) {
            this.deadline = new Date(strDate);
            return;
        }

        this.deadline = this.parseDate(strDate);
    }

    parseDate(strDate) {
        try {
            const [datePart, timePart] = strDate.split(" ");
            const [day, month, year] = datePart.split("/").map(Number);
            const [hour, min] = timePart.split(":").map(Number)

            return new Date(year, month - 1, day, hour, min);
        } catch (e) {
            console.error("Error parsing the date")
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
                this.status = "DOING";
                break;
            case "DONE":
                this.status = "DONE";
                break;
            default:
                this.status = "TODO";
        }
    }
}