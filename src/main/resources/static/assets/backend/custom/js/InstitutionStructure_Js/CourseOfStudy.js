$(document).ready(function () {
    $('.hide').fadeOut();

    $(document).on('click', '.createModal', function() {
        $('.modal-title').text('COURSE OF STUDY  STRUCTURE SETUP');
        $('.form-horizontal').show();
        $('#createModal').modal('show');
    });


    $('.selCategory').on('click', function(e) {
        var selTarget                       =   $(this).attr('id');           //  Hierarchy value of the selected element that triggered a change event
        var selChildTarget                  =   parseInt(selTarget) + 1;     //  Hierarchy value of the child select element by incrementing hierarchy
        var selValue                        =   $(this).val();              //  Value of (id of institutionStructure Parent Value) to get list of children inst str
        var nextSel                         =   $( '#' + selChildTarget);
        var childWrapper                    =   $('.divSelectCategory');
        var obj = {SelTargetHierarchy : selTarget, SelChildTargetHierarchy : selChildTarget, SelTargetValue : selValue, SelTargetElement: nextSel, DivSelChildrenWrapper : childWrapper}
        getInstitutionStructureDropDown(obj);
    });


    $('#selProgrammeCategory').on('change', function (e) {
        var selValue                     =   $(this).val();
        var selProgrammeType             =   $('#selPType');
        var divProgrammeTypeWrapper      =   $('#divProgrammeType');
        getProgrammeTypeDropDown(selValue, selProgrammeType, divProgrammeTypeWrapper);
    });


    $('#btnAddCourseOfStudyDuration').on('click', function (e) {
        var institutionCategoryDescription          =   $('.divSelectCategory:visible:last select').data('description');
        var courseOfStudyValue                      =   $('.divSelectCategory:visible:last select').find(':selected').val();
        var courseOfStudyDescription                =   $('.divSelectCategory:visible:last select').find(':selected').text();

        var programmeTypeDescription                =   $('.divProgrammeType:visible:last select').find(':selected').text();
        var programmeTypeValue                      =   $('.divProgrammeType:visible:last select').find(':selected').val();

        var NoOfLevelValue                          =   $('#txtNoOfLevel').val();
        var txtDurationValue                        =   $('#txtDuration').val();

        if ( courseOfStudyValue <1  || courseOfStudyValue ==0 ){
            showStackTopCenterAlert('notice', 'PLEASE SELECT '+ institutionCategoryDescription  );
            return;
        }
        else if (programmeTypeValue <1 || programmeTypeValue == ''){
            showStackTopCenterAlert('notice', 'PLEASE SELECT PROGRAMME CATEGORY/TYPE'  );
            return;
        }
        else if(NoOfLevelValue <1 || NoOfLevelValue == '' ){
            showStackTopCenterAlert('notice',  'INVALID NO. OF LEVEL'  );
            return;
        }
        else if(txtDurationValue <1 || txtDurationValue == ''){
            showStackTopCenterAlert('notice',  'INVALID DURATION'  );
            return;
        }else {
            $('#divAddCourseOfStudyDuration').fadeIn("slow");
            $("#tblCourseOfStudyDuration tbody").append([
                '<tr>' +
                '<td>'+ courseOfStudyDescription +' <input type="hidden" class="count" id="'+ 'courseDurationList' + $('.count').length + '.institutionStructure'   +'" name="'+ 'courseDurationList' + '[' + $('.count').length + '].' + 'institutionStructure' +'"   value="'+courseOfStudyValue+'"> </td>' +
                '<td>'+ programmeTypeDescription +'  <input type="hidden" id="'+ 'courseDurationList' + $('.count').length + '.institutionProgrammeType'   +'" name="'+ 'courseDurationList' + '[' + $('.count').length + '].' + 'institutionProgrammeType' +'"   value="'+programmeTypeValue+'"> </td>' +
                '<td>'+ NoOfLevelValue+' <input type="hidden"  id="'+ 'courseDurationList' + $('.count').length + '.numberOfLevels'   +'" name="'+ 'courseDurationList' + '[' + $('.count').length + '].' + 'numberOfLevels' +'"  value="'+ NoOfLevelValue +'"></td>' +
                '<td>'+ txtDurationValue +' <input type="hidden"  id="'+ 'courseDurationList' + $('.count').length + '.duration'   +'" name="'+ 'courseDurationList' + '[' + $('.count').length + '].' + 'duration' +'" value="'+ txtDurationValue +'"> </td>' +
                '<td><a class="btn btn-sm btn-danger btnTrashRow"><i class="fa fa-trash text-white"></i> </a></td>' +
                '</tr>'
            ]);
            $('#btnSaveCourseDuration').prop('disabled', false);

        }
    });


    $(document).on("click",'.btnTrashRow', function(){
        btnTrashRemoveRow('divAddCourseOfStudyDuration', 'tblCourseOfStudyDuration', $(this), $('#btnSaveCourseDuration') );
    });


});