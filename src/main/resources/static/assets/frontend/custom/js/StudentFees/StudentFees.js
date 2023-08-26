$(document).ready(function () {

    var studentFeeData = {
        'studentRegNumber': $('#studentRegNumber').attr('data-regNumber'),
        'session': '',
        '_csrf': token
    };

    var url = "/StudentFacing/FeeManagement/GetStudentApplicableFees";

    $('#selSession').on('change', function (e) {

        studentFeeData['session'] = $(this).val();

        if( $(this).val() !== '0' ){

            var request = postData(url, studentFeeData);
            request.done(function( data, textStatus, jqXHR ) {

                var tbody = $('#tblStudentFee').find('tbody');

                tbody.empty();
                if(data.length>0){
                    $.each(data, function (index, data ) {
                        tbody.append(
                            '<tr class="text-left">'+
                            '<td class="text-left"><strong>'+ data.feeCategoryDescription +'</strong></td>' +
                            '<td class="text-left amount">'+ data.amount +'</td>' +
                            '<td class="text-left">-</td>' +
                            '<td class="text-left">-</td>' +
                            '<td class="text-left">'+ data.feeCategoryType +'</td>' +
                            '<td class="text-left">\n' +
                            '<button class="btn btn-sm btn-primary btnSettleFee" type="button" ' +
                            'data-amount="'+data.amount+'" ' + 'data-first_inst="'+ data.firstInstalment +'"' + 'data-second_inst="'+ data.secondInstalment +'"'+
                            'data-allow_part="'+data.allowPartPayment+'" data-title="'+data.feeCategoryDescription +'" >Settle Fee</button>' +
                            '</td>' +
                            '</tr>'
                        ).hide().fadeIn(1000);

                    });
                }else{
                    showStackTopRightAlert('info', 'No Fee Record Found ' +' '+ $("#selSession option:selected").text());
                }
                hideWaitModal();
                $('.amount').formatCurrency({symbol: nairaSymbol});

            }).fail(function(XMLHttpRequest, textStatus, errorThrown ) {
                hideWaitModal();
                showStackTopCenterAlert('error', textStatus);
                // console.log(XMLHttpRequest.responseText);
            });
        }

    });

    $(document).on('click','.btnSettleFee', function(){
        var dataAmount      =   $(this).data('amount');
        var dataTitle       =   $(this).data('title');
        var canPayPart      =   $(this).data('allow_part');
        var instalment      =   $(this).data('instalment');
        var firstInstalment =   $(this).data('first_inst');
        var secondInstalment=   $(this).data('second_inst');

        $('#eTitle').val( dataTitle);
        // $('#eAmount').text( '₦' +dataAmount).formatCurrency({symbol: nairaSymbol});

        var selInstalmentFee = $('#selInstalments');
        selInstalmentFee.empty();

        if(canPayPart){

            selInstalmentFee.append(
                '<option class="optFullAmount" value="'+dataAmount+'" data-installment_type="full"></option>',
                '<option class="optFistInstallment" value="'+firstInstalment+'" data-installment_type="first"></option>',
                '<option class="optSecondInstallment" value="'+secondInstalment+'" data-installment_type="second"></option>'
            );
            $('.optFullAmount').text(dataAmount).formatCurrency({symbol: nairaSymbol}).append(' - Full Payment');
            $('.optFistInstallment').text(firstInstalment).formatCurrency({symbol: nairaSymbol}).append(' - First Installment');
            $('.optSecondInstallment').text(secondInstalment).formatCurrency({symbol: nairaSymbol}).append(' - Second Installment');

            $('#canPayPart').fadeIn();
        }else{
            selInstalmentFee.append(
                '<option class="optFullAmount" value="'+dataAmount+'" data-installment_type="full"></ooption>'
            );
            $('.optFullAmount').text(dataAmount).formatCurrency({symbol: nairaSymbol}).append(' - Full Payment');
            $('#canPayPart').fadeIn();
        }

        $('#divFeeBreakDown').fadeOut();
        $('#divSettleFee').fadeIn(1000);

    });

    $('#aBack').on('click', function (e) {
        $('#divSettleFee').fadeOut(1000);
        $('#divFeeBreakDown').fadeIn(1500);
    });

    $('#btnSettleFee').on('click', function (e) {
        var selSelectedPaymentOption = $('#selPaymentOption option:selected');
       if( selSelectedPaymentOption.val() === "" || selSelectedPaymentOption.data('type') === "" ){
           $('#selPaymentOption').addClass('is-invalid');
           $('#divVerifyPin').fadeOut();

       }else{
           var selType = selSelectedPaymentOption.data('type');
           $('#selPaymentOption').removeClass('is-invalid');
           if (selType.toLowerCase() === "pin"  ){
               $('#divVerifyPin').fadeIn();
           }else if(selType.toLowerCase() === "card"){
               $('#divVerifyPin').fadeOut();
           }else if(selType.toLowerCase() === "scan"){
               $('#divVerifyPin').fadeOut();
           }else if(selType.toLowerCase() === "serial"){
               $('#divVerifyPin').fadeOut();

           }else{

           }
       }

    });

    $('#btnVerifyPayment').on('click', function (e) {
        var pinNumber = $('#txtPinNumber');
        if( pinNumber.val() === ""  ) {
            pinNumber.addClass('is-invalid');
        }
    });


});






