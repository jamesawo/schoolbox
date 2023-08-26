$(document).ready(function () {

    $('.hide').fadeOut();
    $('#frmCreateCourse').parsley();

    $('#btnCreateCourse').on('click', function (e) {
        $('.modal-title').text('CREATE COURSE');
        $('.form-horizontal').show();
        $('#createCourseModal').modal('show');
    });

    //reuse only if  code function remains the same.
    // code can changes based on module specification
    $('.selCategory').on('click',function(e) {
        var selTarget                       =   $(this).attr('id');
        var selChildTarget                  =   parseInt(selTarget) + 1;
        var selValue                        =   $(this).val();
        var nextSel                         =   $( '#' + selChildTarget);
        var childWrapper                    =   $('.divSelectCategory');
        var obj = {
            SelTargetHierarchy : selTarget,
            SelChildTargetHierarchy : selChildTarget,
            SelTargetValue : selValue,
            SelTargetElement: nextSel,
            DivSelChildrenWrapper : childWrapper
        };
        getInstitutionStructureDropDown(obj);

    });

    $('#btnSaveCourse').on('click', function (e) {
        e.preventDefault();
        var institutionStructure                        =   $('.divSelectCategory:visible:last select').find(':selected').val();
        $('#txtInstitutionStructure').val(institutionStructure);

        var form = $('#frmCreateCourse');

        if(form.parsley().isValid()){
            form.submit();
        }else{
            showStackTopRightAlert('warning', 'Some Fields Are Invalid');
        }
    })
});
