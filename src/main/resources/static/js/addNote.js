$(document).ready(function() {
    $('.addBtn').on('click', function(event) {
        event.preventDefault();
        $('.noteForm #title').val('');
        $('.noteForm #description').val('');
        $('.noteForm #addNoteModal').modal();
    });
});