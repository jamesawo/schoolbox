$(document).ready(function(){
    $(document).on('click', '.edit-modal', function() {
        $('.modal-title').text('Edit Category Name');
        $('.form-horizontal').show();
        $('#CategoryDescription').val($(this).data('description'));
        $('#CategoryHierarchy').val($(this).data('hierarchy'));
        $('#CategoryId').val($(this).data('id'));
        $('#editModal').modal('show');
    });

    $("#btnAddCategoryName").on('click', function (e) {
        var obj = {
            divElement: $('#divShowCategory'), txtDescriptionElement: $('#txtCategoryName'), txtHierarchyElement: $('#txtCategoryHierarchy'), tblElementID: 'tblCategory', modelObjDTO: 'categoryList'
        };
        btnAddDescriptionBeforeSave(obj);
    });

    $('.validateInput').keypress(function() {
        removeIsInvalidClassFromTextInput($(this));
    });

    $(document).on("click",'.btnTrashRow',function(){
        btnTrashRemoveRow('divShowCategory', 'tblCategory', $(this), null);
    });


});
