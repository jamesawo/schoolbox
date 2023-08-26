$(document).ready(function () {

    $('#btnCreateSession').on('click', function() {

        $('.modal-title').text('CREATE SESSION');
        $('.form-horizontal').show();
        $('#createSession').modal('show');
    });

    $('#btnSetSemester').on('click', function() {

        $('.modal-title').text('SET SEMESTER');
        $('.form-horizontal').show();
        $('#setSemester').modal('show');
    });

    $('#btnSetSession').on('click', function() {

        $('.modal-title').text('SET SESSION');
        $('.form-horizontal').show();
        $('#setSession').modal('show');
    });

    $('#selProgrammeTypeCreateSession').on('change', function (e) {
        var programmeTypeId     =   $(this).find(':selected').val();
        var selSession          =  { selSessionElement : $('#txtCurrentSessionCreate'), selSessionElementValue: null };

        if (programmeTypeId > 0){
            getCurrentSemesterSession(programmeTypeId, selSession, null);
        }else {
            selSession.selSessionElement.val('');
        }
    });

    $('#selProgrammeTypeSetSemester').on('change', function (e) {
        var programmeTypeId     =   $(this).find(':selected').val();
        var selSession          =  { selSessionElement : $('#txtSessionSetSemester'), selSessionElementValue: null };

        if (programmeTypeId > 0){
            getCurrentSemesterSession(programmeTypeId, selSession, null);
        }else {
            selSession.selSessionElement.val('');
        }
    });

    $('#selProgrammeTypeSetSession').on('change', function (e) {
        var programmeTypeId     =   $(this).find(':selected').val();
        var selSession          =  { selSessionElement : $('#txtSessionSetSession'), selSessionElementValue: null };
        var selSemester         =  { selSemesterElement : $('#txtSemesterSetSession')  };

        if (programmeTypeId > 0){
            getCurrentSemesterSession(programmeTypeId, selSession, selSemester);
        }else {
            selSession.selSessionElement.val('');
            selSemester.selSemesterElement.val('');
        }
    });



});