function validateField() {
    var title = document.getElementById("noteTitle").value;
    var description = document.getElementById("noteDescription").value;

    if (title.length < 6 || title.length > 30) {
        alert("Title size should be more than 6 and less than 30");
        return false;
    }

    if (description.length < 6) {
        alert("Description size should be more than 6");
        return false;
    }

    return false;
}