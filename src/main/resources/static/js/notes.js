$(document).ready(function() {
    $('.addBtn, .table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).attr('id');
        if (text == "eBtn") {
            $.get(href, function(note, status) {
                document.getElementById('id').value = note.id;
                $('.myForm #title').val(note.title);
                $('.myForm #description').val(note.description);
            })
            $('.myForm #updateNoteModal').modal();
        } else {
            $('.noteForm #title').val('');
            $('.noteForm #description').val('');
            $('.noteForm #addNoteModal').modal();
        }
    });

    $('.table .delBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).attr('id');
        $('#delNoteModal #delNoteId').attr('href', href);
        $('#delNoteModal').modal();
    });

    $('.addBtn').on('click', function(event) {
        $('.addNote #title').val('');
        $('.addNote #description').val('');
        $('.addNote #addNoteModal').modal();
    });

});