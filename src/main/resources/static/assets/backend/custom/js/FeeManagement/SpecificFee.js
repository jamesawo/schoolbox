$(document).ready(function () {

    // showWaitModal('back');

    $('.hide').fadeOut();

    $('#btnAddSpecificFee').on('click', function(e) {
        $('.modal-title').text('SPECIFIC FEE CREATION SETUP');
        $('.form-horizontal').show();
        $('#createSpecificFeeModal').modal('show');
        $('.hide').fadeOut(); $('.chkAllowPartPayment').prop('checked', false);
    });

    //SpecificFee Setup
    $('.selProgrammeCategory').on('change', function (e) {
        var selValue                     =   $(this).val();
        var selProgrammeType             =   $('#selPType');
        var divProgrammeTypeWrapper      =   $('#divProgrammeType');
        getProgrammeTypeDropDown(selValue, selProgrammeType, divProgrammeTypeWrapper);
    });
    //GlobalFee Setup
    $('.selProgrammeCategoryGlobalFee').on('change', function (e) {
        var selValue                     =   $(this).val();
        var selProgrammeType             =   $('#selPTypeGlobal');
        var divProgrammeTypeWrapper      =   $('#divProgrammeTypeGlobal');
        getProgrammeTypeDropDown(selValue, selProgrammeType, divProgrammeTypeWrapper);
    });

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

    $('#btnAddGlobalFee').on('click', function(e) {
        $('.modal-title').text('GLOBAL FEE SETUP');
        $('.form-horizontal').show();
        $('#createGlobalFeeModal').modal('show');
        $('.hide').fadeOut(); $('.chkAllowPartPayment').prop('checked', false);
    });

    $('#btnSaveSpecificFee').on('click', function (e) {
        e.preventDefault();
        var programmeTypeValue                      =   $('.divProgrammeType:visible:last select').find(':selected');
        var courseOfStudyObj                        =   $('.divSelectCategory:visible:last select').find(':selected');  //todo::remove duplicate selector
        var courseOfStudyCategory                   =   $('.divSelectCategory:visible:last select').data('categoryid');
        var courseOfStudyValue                      =   courseOfStudyObj.val();
        var courseOfStudyParent                     =   parseInt(courseOfStudyCategory) - 1;
        var courseOfStudyParentValue                =   $('#'+courseOfStudyParent).val();

        $('#txtSpecificFeeProgrammeTypeValue').val(programmeTypeValue.val());

        //check if institution structure is selected and ensure it is not the first hierarchy
        //if it is not selected then use the parent institution structure

        if(courseOfStudyValue === ''){
            //all faculty is selected
            console.log("First Category Selected");
            $('#txtSpecificFeeInstitutionStructureValue').val(0);

        }else if(courseOfStudyValue == 0){
            //select the parent of the last selected hierarchy
            $('#txtSpecificFeeInstitutionStructureValue').val(courseOfStudyParentValue);
        }else{
            $('#txtSpecificFeeInstitutionStructureValue').val(courseOfStudyValue);

        }
        $('#frmSpecificFeeSetup').submit();
    });


    //handle institution structure input field look up
    $("#txtInstitutionStructure").easyAutocomplete( {
        url: function(phrase) {return "/API/SLMS/Common/Search/InstitutionStructure/DescriptionSearch?phrase=" + phrase + "&format=json";},
        getValue: "text",
        list: {
            onSelectItemEvent: function(){
                var id = $('#txtInstitutionStructure').getSelectedItemData().value;
                $('#txtInstitutionStructureHidden').val(id);
            },
            match: {
                enabled: true
            }
        }
    });

    //handle allow part payment checkbox toggle

    $('.chkAllowPartPayment').change(function() {
        var display = $('.hide');
        if(this.checked) {
            display.fadeIn();
        }else{
            display.fadeOut();
        }
    });

    //todo::validate instalments amount to sum up to total amount before saving.
});
