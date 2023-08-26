$(document).ready(function () {

    $(document).on('click', '.edit-modal', function() {
        $('.modal-title').text('UPDATE SEMESTER');
        $('.form-horizontal').show();
        $('#semesterDescriptionEdit').val($(this).data('description'));
        $('#semesterHierarchyEdit').val($(this).data('hierarchy'));
        $('#semesterIdEdit').val($(this).data('id'));
        $('#editModal').modal('show');
    });

    $('#btnAddSemesterDescription').on('click', function (e) {
        var obj = {
            divElement: $('#divShowSemester'),txtDescriptionElement: $('#txtSemesterDescription'), txtHierarchyElement: $('#txtSemesterHierarchy'), tblElementID: 'tblSemester', modelObjDTO: 'institutionSemesterList'
        };
        btnAddDescriptionBeforeSave( obj );
    });

    $('.validateInput').keypress(function() {
        removeIsInvalidClassFromTextInput($(this));
    });


    $(document).on("click",'.btnTrashRow',function(){
        btnTrashRemoveRow('divShowSemester', 'tblSemester', $(this), null);
    });

});