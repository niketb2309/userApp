function validateField() {
    var title = document.getElementById("noteTitle").value;
    var description = document.getElementById("noteDescription").value;

    if (title.length < 6 || title.length > 30) {
        event.preventDefault();
        alert("Title size should be more than 6 and less than 30");
        document.getElementById("noteTitle").focus();
    }

    if (description.length < 6) {
        event.preventDefault();
        alert("Description size should be more than 6");
        document.getElementById("noteDescription").focus();
    }
    return true;
}

function validateUpdateFields() {
    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;

    if (title.length < 6 || title.length > 30) {
        event.preventDefault();
        alert("Title size should be more than 6 and less than 30");
        document.getElementById("title").focus();
    }

    if (description.length < 6) {
        event.preventDefault();
        alert("Description size should be more than 6");
        document.getElementById("description").focus();
    }
    return true;
}
