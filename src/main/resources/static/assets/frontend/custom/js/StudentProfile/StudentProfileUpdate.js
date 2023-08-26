$(document).ready(function () {

   $('#selState').on('change', function (e) {
      getLocationLocalsByStateId($(this).val(), $('.lga') );
   });

   $("#file").change(function() {
      readImageURL(this, $('.imgPassport'));
   });

});
