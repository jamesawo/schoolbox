$(document).ready(function () {
    // Fill Edit Modal With Values
    $(document).on('click', '.edit-modal', function() {

        $('.modal-title').text('Edit Institution Level ');

        $('.form-horizontal').show();
        $('#txtId').val($(this).data('id'));
        $('#txtDescription').val($(this).data('description'));
        $('#txtAlternative').val($(this).data('alternative'));
        $('#txtHierarchy').val($(this).data('hierarchy'));
        $('#editModal').modal('show');
    });
});