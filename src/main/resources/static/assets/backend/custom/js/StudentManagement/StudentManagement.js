$(document).ready(function () {
    $('.hide').hide();

    var sessionObj = { selSessionElement: $('#selCurrentSession'), selSessionElementValue: $('#selCurrentSessionValue') };

    $('#chkStatusHeader').on('click', function (e) {
        $('.chkStatus').prop('checked', this.checked);
    });

    $('#btnAddNewStudent').on('click', function(e) {
        $('.modal-title').text('ADD NEW STUDENT');
        $('.form-horizontal').show();
        $('#createAddNewStudentModal').modal('show');
    });

    $('.selPType').on('change', function (e) {
        $('#txtProgrammeTypeText').val('');
        $('#txtProgrammeTypeValue').val('');
        $('#selCurrentSession').val('');

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


    $('#selPType').on('change', function (e) {

        var programmeTypeValue          =   $(this).find(':selected').val();
        var programmeTypeText           =   $(this).find(':selected').text();

        if ( $(this).find(':selected').text() !== 'Select' ){
            $('#txtProgrammeTypeValue').val(programmeTypeValue);
            $('#txtProgrammeTypeText').val(programmeTypeText);
            getCurrentSemesterSession(programmeTypeValue, sessionObj, null);
        }

    });

    $('.selCategory').on('change', function (e) {
        $('#txtCourseOfStudyText').val('');
        $('#txtCourseOfStudyValue').val('');

        var maxID = -1;
        $('.selCategory').each(function() {
            var myid = parseInt($(this).attr('id'),10);
            if( maxID < myid ) maxID = myid;
        });

        $('#'+maxID).on('change',function (e) {
            var lastCourseOfStudyText =  $(this).find(':selected').text();
            if ( lastCourseOfStudyText !== 'Select'){
                $('#txtCourseOfStudyText').val( $(this).find(':selected').text() );
                $('#txtCourseOfStudyValue').val( $(this).find(':selected').val() );
            }
        });
    });

    //Autocomplete student registration number field
    $('#txtStudentRegNumber').easyAutocomplete({
        url: function(phrase) {
            return "/API/SLMS/Common/Search/StudentManagement/NameSearch?phrase=" + phrase + "&format=json";
        },
        getValue: "text",
        list: {
            onSelectItemEvent: function(){
                var id = $('#txtStudentRegNumber').getSelectedItemData().value;
                var surname = $('#txtStudentRegNumber').getSelectedItemData().studentSurname;
                var firstName = $('#txtStudentRegNumber').getSelectedItemData().studentFirstName;
                var lastName = $('#txtStudentRegNumber').getSelectedItemData().studentLastName;
                $('#txtStudentRegNumberHidden').val(id);
                $('#txtStudentName').val( surname + ' ' + firstName + ' ' + lastName   );
            },
            match: {
                enabled: true
            }
        }
    });

    //use on each page, creating a global instance causes bugs
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

    $('#selProgrammeType').on('change', function (e) {
        console.log($(this).val());
        var object = {
            "url" : "/API/SLMS/InstitutionStructure/SessionByProgrammeType/",
            "parentId": $(this).val(),
            "selElementToPopulate" : $('#selSession')
        };
        if ( $(this).val() != '' ||  $(this).find(':selected').text() != 'Select' ){
            populateDropDownList(object);
        }else {
            showStackTopCenterAlert('notice', "Invalid Selection");
        }
    });

    $('#btnSingleSearch').on('click', function (e) {
        e.preventDefault();
        $('#txtCommand').val("Single");
        var studentRegNumber         =      $('#txtStudentRegNumber').val();
        var studentName              =      $('#txtStudentName').val();
        if( studentRegNumber == null || studentRegNumber == "" && studentName == null || studentName == ""   ){
            validate('validateText');
            $('#txtStudentRegNumber').focus();
            return;
        }else{
            $('#frmStudentSearch').submit();
        }

    });

    //
    $('#txtStudentRegNumber' ).on('keypress', function (e) {

        $(this).removeClass('is-invalid');

    });

    $('#btnMultipleSearch').on('click', function (e) {
        e.preventDefault();
        $('#txtCommand').val("Multiple");

        // var instStructure = $('#txtInstitutionStructure').val();
        // if( instStructure.length < 1 || instStructure == "" || instStructure == null  ){
        //     validate('validateMultipleSearch');
        //     return;
        // }else{
        //     $('#frmStudentSearch').submit();
        // }

        $('#frmStudentSearch').submit();

    });

    //add block to common
    $('#selPageSize').on('change', function (e) {
        $('#pageSize').val($(this).val());
        $('#frmStudentSearch').submit();
    });

    $('.pageItem').on('click', function (e) {
        $('#pageNumber').val($(this).data('page') - 1);
        $('#frmStudentSearch').submit();

    });

    $('#previousPage').on('click', function (e) {
        if(! $(this).hasClass('disabled')){
            var currentPageNumber =  $('#pageNumber').val();
            $('#pageNumber').val(parseInt(currentPageNumber ) - 1);
            $('#frmStudentSearch').submit();
        }

    });

    $('#nextPage').on('click', function (e) {
        if(! $(this).hasClass('disabled')){
            var currentPageNumber =  $('#pageNumber').val();
            $('#pageNumber').val(parseInt(currentPageNumber ) + 1);
            $('#frmStudentSearch').submit();
        }
    });

    $('#btnActivateStudent').on('click', function (e) {
        changeStudentActiveStatus("ACTIVATE");
    });

    $('#btnDeactivateStudent').on('click', function (e) {
        changeStudentActiveStatus( "DEACTIVATE");
    });

    function changeStudentActiveStatus(command) {

        var studentIdList =  [];
        $("#studentSearchResultTable input[type=checkbox]:checked").each(function (e) {
            if ($(this).val() !== 'header' ){
                studentIdList.push($(this).val());
            }
        });

        console.log(studentIdList);
        console.log("-------------------------");
        console.log(JSON.stringify(studentIdList));

        if (command === "ACTIVATE"){
            console.log("Activating Students");
            $.ajax({
                type:"POST",
                url:"/SLMS/StudentManagement/StudentList/Update/Status/",
                // headers:{
                //     "Content-Type": "application/json",
                //     "Accept": "application/json"
                // },
                data: studentIdList,
                success: function (data) {
                    console.log(data);
                },
                error: function (jqHXR, textStatus, errorThrown) {
                    console.log(textStatus + " " + errorThrown);
                }
            });
        }

        if (command === "DEACTIVATE"){
            console.log("Deactivating Students");
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/SLMS/StudentManagement/StudentList/Update/Status/",
                data: JSON.stringify(studentIdList),
                dataType: 'json',
                cache: false,
                timeout: 600000,
                success: function (data) {
                    console.log("SUCCESS : ", data);
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                }
            });

        }
    }

    $("#btnDownloadReportPdf").on('click', function (e) {
        $("#reportFormat").val("PDF");
        var frmStudentSearch = $("#frmStudentSearch");
        var action = frmStudentSearch.attr('action');
        frmStudentSearch.attr({'action': 'Student/GenerateReport', "target":""});
        frmStudentSearch.submit();
        frmStudentSearch.attr({"action": action, "target": ''});
    });
    $("#btnDownloadReportExcel").on('click', function (e) {
        $("#reportFormat").val("XLS");
        var frmStudentSearch = $("#frmStudentSearch");
        var action = frmStudentSearch.attr('action');
        frmStudentSearch.attr({'action': 'Student/GenerateReport', "target":""});
        frmStudentSearch.submit();
        frmStudentSearch.attr({"action": action, "target": ''});

    });

});


