$(document).ready(function () {
    $('#btnAddFeeCategory').on('click', function(e) {
        $('.modal-title').text('FEE CATEGORY CREATION DETAILS');
        $('.form-horizontal').show();
        $('#createModal').modal('show');
    });

    $('.btnEdit').on('click', function(e) {

        $('.modal-title').text('UPDATE FEE CATEGORY DETAILS');
        $('#txtEditCategoryDescription').val($(this).data('description'));
        $('#selEditCategoryType').val($(this).data('type'));
        $('#txtEditCategoryId').val($(this).data('id'));
        $('.form-horizontal').show();
        $('#editModal').modal('show');
    });

    $('#chkStatusHeader').on('click', function (e) {
        $('.chkStatus').prop('checked', this.checked);
    });

});