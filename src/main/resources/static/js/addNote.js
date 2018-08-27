$(document).ready(function() {
    $('.addBtn').on('click', function(event) {
        $('.myForm #title').val('');
        $('.myForm #description').val('');
        $('.myForm #addNoteModal').modal();
    });
});