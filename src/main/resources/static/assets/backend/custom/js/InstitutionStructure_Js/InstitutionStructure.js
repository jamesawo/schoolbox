$(document).ready(function () {

    $('.hide').fadeOut();

    $('.selCategory').on('click', function(e) {
        /*
        {
            selTarget: Select Element with hierarchy of 1
            selChildTarget: Select element with hierarchy > 1
            nextSel: next select element from 1 = element with id 2
            childWrapper: div with class .divSelectCategory (div element with hierarchy > 1 is has this class)

        }

         */

        var selTarget                       =   $(this).attr('id');           //  Hierarchy value of the selected element that triggered a change event
        var selChildTarget                  =   parseInt(selTarget) + 1;     //  Hierarchy value of the child select element by incrementing hierarchy
        var selValue                        =   $(this).val();              //  Value of (id of institutionStructure Parent Value) to get list of children inst str
        var nextSel                         =   $( '#' + selChildTarget);
        var childWrapper                    =   $('.divSelectCategory');
        var obj = {SelTargetHierarchy : selTarget, SelChildTargetHierarchy : selChildTarget, SelTargetValue : selValue, SelTargetElement: nextSel, DivSelChildrenWrapper : childWrapper};
        getInstitutionStructureDropDown(obj);
    });

    $('#btnSaveInstitutionStructure').on('click', function (e) {
        e.preventDefault();

        var hierarchy = $('.divSelectCategory:visible:last select').attr('id');
        var category = $('.divSelectCategory:visible:last select').data('categoryid');
        var parent  =   parseInt(hierarchy) -1 ;
        var parentValue = $('#' + parent ).find(':selected').val();

        if(category == 1){
            $('#txtInsParentId').val(0);
        }else {
            $('#txtInsParentId').val(parentValue);
        }
        $('#txtInsGroupId').val(category);
        $('#frmCreateInsStructure').submit();

    });

});