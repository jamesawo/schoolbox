$(document).ready(function () {

    // Create Function

    $('#tblProgrammeCategoryDisplay').DataTable();
    $('#selProgrammeTypes').hide();
    $('#divProgrammeType').hide();


    $('#selProgrammeCategory').on('change', function (e) {
        var programmeCategoryValue  =   $(this).val();
        var selProgrammeType        =   $('#selProgrammeTypes');
        var divProgrammeType        =   $('#divProgrammeType');

        var obj = {
            programmeCategoryValue : programmeCategoryValue,  selProgrammeType : selProgrammeType,  divProgrammeType : divProgrammeType
        }

        getInstitutionProgrammeTypesDropDown( obj );

        // selProgrammeType.empty();
        // selProgrammeType.append('<option value="'+0+'">'+"Select"+'</option>');
        // selProgrammeType.fadeIn('slow');
        // divProgrammeType.fadeIn('slow');
        // if (programmeCategoryValue > 0)
        // {
        //     $("#modalWait").show();
        //
        //     $.ajax({
        //         url:"/SLMS/InstitutionStructure/ProgrammeTypeSetup/" +programmeCategoryValue,
        //         type:"GET",
        //         success: function (data) {
        //             $.each(data, function(index, types){
        //                 selProgrammeType.append(['<option value="'+types.id+'">'+types.description+'</option>']);
        //             });
        //             $("#modalWait").hide();
        //         },
        //         error: function (jqHXR, textStatus, errorThrown) {
        //             console.log( textStatus );
        //             $("#modalWait").hide();
        //         }
        //     });
        //
        // }else{
        //
        //     selProgrammeType.fadeOut('slow');
        //     divProgrammeType.fadeOut('slow');
        //     selProgrammeType.empty();
        // }

    });

    $('#selCategory').on('change', function (e) {
        var value =  $(this).val();
        if(value !== ""){
            $('#txtCategoryValue').val( value );
            $('#frmGetProgrammeType').submit();
       }

    });

    //Create Modal
    $(document).on('click', '.createModal', function() {
        $('.modal-title').text('CREATE PROGRAMME TYPE SETUP');
        $('.form-horizontal').show();
        $('#createModal').modal('show');
    });


    //Edit Function
    $('#selEditProgrammeCategory').hide();
    $('#divEditProgrammeCategory').hide();

    //Edit Modal
    $(document).on('click', '.editModal', function() {
        $('.modal-title').text('EDIT PROGRAMME TYPE SETUP');
        $('#txtEditId').val($(this).data('id'));
        $('#txtType').val($(this).data('type'));

        $('#txtEditParentCategory').val($(this).data('parent'));
        $('#txtEditDescription').val($(this).data('description'));
        $('#txtEditCode').val($(this).data('code'));
        $('#txtEditAliasDescription').val($(this).data('alias'));
        $('#txtEditMerchantId').val($(this).data('merchant'));
        $('#txtEditCertificate').val($(this).data('certificate'));

        $('.form-horizontal').show();
        $('#editModal').modal('show');

        if ($(this).data('type') == 1){
            $('#divGroup').hide();
        }
        if ($(this).data('type') == 2){
            $('#divGroup').show();
        }


    });

    $('#chkEditParentCategory').on('click', function (e) {
        $('#selEditProgrammeCategory').toggle('slow');
        $('#divEditProgrammeCategory').toggle('slow');
    });

    $('#selEditProgrammeCategory').on('change', function (e) {

        $('#txtEditParentCategory').val( $( "#selEditProgrammeCategory option:selected" ).data('description') );
        $('#txtEditParentCategoryValue').val($(this).val());
    });


})