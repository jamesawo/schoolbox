$(document).ready(function () {


    $('#chkAllowRegNumberUpdate').on('click', function () {
       $('#txtRegNumber').prop('readonly', function (i, v) {return !v;});
    });

    $("#file").change(function() {
        readImageURL(this, $('.imgPassport'));
    });

    $('.selCategory').on('click', function(e) {

        var selTarget                       =   $(this).attr('id');
        var selChildTarget                  =   parseInt(selTarget) + 1;
        var selValue                        =   $(this).val();
        var nextSel                         =   $( '#' + selChildTarget);
        var childWrapper                    =   $('.divSelectCategory');
        var obj = {SelTargetHierarchy : selTarget, SelChildTargetHierarchy : selChildTarget, SelTargetValue : selValue, SelTargetElement: nextSel, DivSelChildrenWrapper : childWrapper};
        getInstitutionStructureDropDown(obj);
    });


    $('#selProgrammeCategory').on('change', function (e) {
        var programmeCategoryValue  =   $(this).val();
        var selProgrammeType        =   $('#selProgrammeTypes');
        var divProgrammeType        =   $('#divProgrammeType');
        var obj = {
            programmeCategoryValue : programmeCategoryValue,  selProgrammeType : selProgrammeType,  divProgrammeType : divProgrammeType
        };
        getInstitutionProgrammeTypesDropDown(obj);
    });

    $('#selState').on('change', function(){
        showWaitModal();
        getLocationLocalsByStateId( $(this).val() , $('#selLocals') );
    });

});
