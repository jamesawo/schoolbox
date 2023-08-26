// https://code.google.com/archive/p/jquery-formatcurrency/wikis/Usage.wiki
// Token phrase
var token = $("meta[name='_csrf']").attr("content");
var nairaSymbol = '₦';
//Label to trigger file input element
;(function ($, window, document, undefined) {
    $('.input-file').each(function () {
        var $input = $(this),
            $label = $input.next('label'),
            labelVal = $label.html();

        $input.on('change', function (e) {

            var fileName = '';

            if (this.files && this.files.length > 1) {
                fileName = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
            } else if (e.target.value) {
                fileName = e.target.value.split('\\').pop();
            }

            if (fileName) {
                $label.find('span').html(fileName);
            } else {
                $label.html(labelVal);
            }

        });

        // Firefox bug fix
        $input
            .on('focus', function () {
                $input.addClass('has-focus');
            })
            .on('blur', function () {
                $input.removeClass('has-focus');
            });
    });
})(jQuery, window, document);
//

//preview image before upload

function readImageURL(input, srcElement) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            srcElement.attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}


// Alert Notification common
function showStackTopCenterAlert(type, message) {
    if (typeof window.stackTopCenter === 'undefined') {
        window.stackTopCenter = {
            'dir1': 'down',
            'firstpos1': 25
        };
    }
    var opts = {
        title: "Error 'Title' ",
        text: "Error. 'Message'",
        stack: window.stackTopCenter
    };
    switch (type) {
        case 'error':
            opts.title = 'Error';
            opts.text = message;
            opts.type = 'error';
            break;
        case 'info':
            opts.title = 'Info';
            opts.text = message;
            opts.type = 'info';
            break;
        case 'success':
            opts.title = 'Success';
            opts.text = message;
            opts.type = 'success';
            break;
        case 'notice':
            opts.title = 'Warning';
            opts.text = message;
            opts.type = 'notice';
            break;
    }
    PNotify.alert(opts);
}

//todo::refactor to use one function for all stack types
function showStackTopRightAlert(type, message) {

    PNotify.alert({text: message, type: type})
}

//

// Trash Btn to remove tr row
function btnTrashRemoveRow(divId, tblId, btnObj, btnSaveObj) {

    if ($('#' + tblId).find('tr').length === 2) {
        btnObj.closest('tr').remove();
        $('#' + divId).fadeOut();
        btnSaveObj.prop('disabled', true);
    } else {
        btnObj.closest('tr').remove();
    }
}

//Add Text to display table before save (used in Semester Setup view)
function btnAddDescriptionBeforeSave(obj) {

    if (obj.txtDescriptionElement.val().length < 1) {
        obj.txtDescriptionElement.addClass('is-invalid');
        obj.txtDescriptionElement.focus();
    } else if (obj.txtHierarchyElement.val().length < 1 || obj.txtHierarchyElement.val() < 1) {
        obj.txtHierarchyElement.addClass('is-invalid');
        obj.txtHierarchyElement.focus();
    } else {
        obj.divElement.fadeIn("slow");
        $("#" + obj.tblElementID + " tbody").append(
            '<tr>' +
            '<td>' + obj.txtDescriptionElement.val() + '<input type="hidden" class="count" id="' + obj.modelObjDTO + $('.count').length + '.description' + '" name="' + obj.modelObjDTO + '[' + $('.count').length + '].' + 'description' + '"   value="' + obj.txtDescriptionElement.val() + '">' +
            '<td>' + obj.txtHierarchyElement.val() + '<input type="hidden" id="' + obj.modelObjDTO + $('.count').length + '.hierarchy' + '" name="' + obj.modelObjDTO + '[' + $('.count').length + '].' + 'hierarchy' + '"   value="' + obj.txtHierarchyElement.val() + '">' +

            '<td class="text-center">' +
            '<a  class="btn btn-sm btn-danger btnTrashRow">' +
            '<i class="fa fa-trash text-white" aria-hidden="true"></i> </a></td>' +
            '</tr>'
        );

    }
}

//remove validation class from text field
function removeIsInvalidClassFromTextInput(txtElement) {
    if (txtElement.val().length > 0 || txtElement.val() > 0 || txtElement.val() != '') {
        txtElement.removeClass('is-invalid');
    }
}

//Multiple Drop down level for programme type.
function getProgrammeTypeDropDown(selProgrammeCategoryValue, selProgrammeTypeElement, divProgrammeTypeWrapperElement) {

    if (selProgrammeCategoryValue > 0) {
        selProgrammeTypeElement.empty();
        selProgrammeTypeElement.append(['<option value="0">Select</option>']);
        showWaitModal('back');
        $.ajax({
            url: "/SLMS/InstitutionStructure/ProgrammeTypeSetup/" + selProgrammeCategoryValue,
            type: "GET",
            success: function (data) {
                $.each(data, function (index, category) {
                    selProgrammeTypeElement.append(['<option value="' + category.id + '">' + category.description + '</option>']);
                });
                divProgrammeTypeWrapperElement.fadeIn('slow');
                hideWaitModal('back');
            },
            error: function (jqHXR, textStatus, errorThrown) {
                console.log(textStatus);
                console.log(errorThrown);
                hideWaitModal('back');
            }
        });
    } else if (selProgrammeCategoryValue === 0 || selProgrammeCategoryValue === '') {
        divProgrammeTypeWrapperElement.fadeOut('slow');
    } else {
        divProgrammeTypeWrapperElement.fadeOut('slow');

    }


}

//Institution structure drop down helper (used in __fragments::institution)
// var obj = {SelTargetHierarchy : selTarget, SelChildTargetHierarchy : selChildTarget, SelTargetValue : selValue, SelTargetElement: nextSel, DivSelChildrenWrapper : childWrapper}
function getInstitutionStructureDropDown(obj) {

    obj.SelTargetElement.empty();
    obj.SelTargetElement.append('<option value="' + 0 + '">' + "Select" + '</option>');
    if (obj.SelTargetValue > 0) {
        showWaitModal('back');
        $.ajax({
            url: "/SLMS/InstitutionStructure/FindInstitutionStructures/Category/Parent/" + obj.SelTargetValue,
            type: "GET",
            success: function (data) {
                $.each(data, function (index, category) {
                    obj.SelTargetElement.append(['<option value="' + category.id + '">' + category.description + '</option>']);
                });
                $('.category' + obj.SelChildTargetHierarchy).fadeIn('slow');
                hideWaitModal('back');
            },
            error: function (jqHXR, textStatus, errorThrown) {
                console.log(textStatus);
                hideWaitModal('back');
            }
        });

    } else if (obj.SelTargetValue === 0) {
        obj.DivSelChildrenWrapper.each(function () {
            if ($(this).data('number') > obj.SelTargetHierarchy) {
                $(this).fadeOut('slow');
            }
        });
    } else {
        obj.DivSelChildrenWrapper.each(function () {
            if ($(this).data('number') > obj.SelTargetHierarchy) {
                $(this).fadeOut('slow');
            }
        });
    }
}

//get programme Type dropdown

function getInstitutionProgrammeTypesDropDown(obj) {

    obj.selProgrammeType.empty();
    obj.selProgrammeType.append('<option value="' + 0 + '">' + "Select" + '</option>');
    obj.selProgrammeType.fadeIn('slow');
    obj.divProgrammeType.fadeIn('slow');
    if (obj.programmeCategoryValue > 0) {

        showWaitModal('back');

        $.ajax({
            url: "/SLMS/InstitutionStructure/ProgrammeTypeSetup/" + obj.programmeCategoryValue,
            type: "GET",
            success: function (data) {
                $.each(data, function (index, types) {
                    obj.selProgrammeType.append(['<option value="' + types.id + '">' + types.description + '</option>']);
                });
                // hideWaitModal();
                hideWaitModal('back');
            },
            error: function (jqHXR, textStatus, errorThrown) {
                console.log(textStatus);
                // hideWaitModal();
                hideWaitModal('back');
            }
        });

    } else {

        obj.selProgrammeType.fadeOut('slow');
        obj.divProgrammeType.fadeOut('slow');
        obj.selProgrammeType.empty();
    }
}

//show wait modal
function showWaitModal(postion) {

    if (postion == null) {
        $('#overlay').addClass('show');
    } else if (postion === 'back') {

        $('#back-overlay').addClass('show');
        $('#modalWait').show();

    } else if (postion === 'front') {
        $('#overlay').addClass('show');
    } else {
        $('#overlay').addClass('show');

    }


}

// hide wait modal
function hideWaitModal(position) {

    if (position == null) {
        $('#overlay').removeClass('show');

    } else if (position === 'back') {
        $('#back-overlay').removeClass('show');
        $('#modalWait').hide();
    } else if (postion === 'front') {
        $('#overlay').removeClass('show');
    } else {
        $('#overlay').removeClass('show');

    }

}

//Validate field if empty or val less than 1 (create new ins category)
function validate(selector) {
    var checker = false;
    $('.' + selector).each(function () {

        if ($(this).val().length === 0 || $(this).val() < 1 || $(this).val() === '') {
            $(this).addClass('is-invalid');
            checker = true;
        }
    });
    return checker;
}

// get current application session and semester by programme type
function getCurrentSemesterSession(selProgrammeTypeId, selSessionObj, selSemesterObj) {

    showWaitModal('back');
    $.ajax({
        url: "/SLMS/InstitutionStructure/Active/SessionByProgrammeType/" + selProgrammeTypeId,
        type: "GET",
        success: function (data) {
            hideWaitModal('back');
            if (selSessionObj != null) {
                if (selSessionObj.selSessionElement != null) {
                    selSessionObj.selSessionElement.val(data['institutionSession'].description);
                }
                if (selSessionObj.selSessionElementValue != null) {
                    selSessionObj.selSessionElementValue.val(data['institutionSession'].id);
                }
            }
            if (selSemesterObj != null) {

                if (selSemesterObj.selSemesterElement != null) {
                    selSemesterObj.selSemesterElement.val(data['institutionSemester'].description);
                }
            }
        },
        error: function (jqHXR, textStatus, errorThrown) {
            console.log(textStatus);
            hideWaitModal('back');
        }
    });
}


function getLocationLocalsByStateId(stateId, selLocalEle) {
    showWaitModal();
    selLocalEle.empty();
    selLocalEle.append(['<option value="">Select</option>']);

    $.ajax({
        url: "/API/SLMS/SLMS_Common/Location/LgaList/" + stateId,
        type: "GET",
        success: function (data) {
            hideWaitModal();
            if (selLocalEle != null) {
                $.each(data, function (index, data) {
                    selLocalEle.append(['<option value="' + data.id + '">' + data.description + '</option>']);
                });
            }
        },
        error: function (jqHXR, textStatus, errorThrown) {
            showStackTopCenterAlert("error", textStatus + ': ' + errorThrown);
            hideWaitModal();
        }
    });
}


function easyCompleteField(autoCompleteTextField, object) {
    autoCompleteTextField.easyAutocomplete(object);
}

//Populate select dropdown with data list
//object={url, parentId, elementToPopulate}
function populateDropDownList(object) {

    showWaitModal();
    object.selElementToPopulate.empty();
    object.selElementToPopulate.append(['<option value="0">Select</option>']);

    $.ajax({
        url: object.url + object.parentId,
        type: "GET",
        success: function (data) {
            hideWaitModal();

            $.each(data, function (index, data) {
                object.selElementToPopulate.append(['<option value="' + data.id + '">' + data.description + '</option>']);
            });

        },
        error: function (jqHXR, textStatus, errorThrown) {
            console.log(textStatus);
            showStackTopCenterAlert("error", textStatus);
            hideWaitModal();
        }
    });

}

//todo::Add token beforeSend ----- wip ('_csrf": token) to data before sending
function postData(url, data) { // url: string, data: {//
    showWaitModal();
    return $.ajax({
        url: url,
        type: "post",
        data: data
    });

}

function getData(url) { // url: string, data: {}

    return $.ajax({
        url: url,
        type: "GET",
    });

}



