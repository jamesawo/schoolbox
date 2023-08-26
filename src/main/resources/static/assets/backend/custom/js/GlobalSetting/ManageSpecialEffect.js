$(document).ready(function () {

   $("#logo").change(function() {
      readImageURL(this, $('#imgLogo'));
   });

   $("#receiptSignature").change(function() {
      readImageURL(this, $('#imgReceiptSignature'));
   });

   $("#registrarSignature").change(function() {
      readImageURL(this, $('#imgRegistrarSignature'));
   });

   $("#alternativeLogo").change(function() {
      readImageURL(this, $('#imgAlternativeLogo'));
   });


});