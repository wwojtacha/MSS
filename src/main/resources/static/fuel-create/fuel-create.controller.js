angular.module('fuel-create')
.controller('FuelCreateController', function(fuelService, $location) {
   var vm = this;

   vm.fuel = {};

   vm.create = create;

   function create() {
       fuelService.create(vm.fuel)
           .then(function() {
               $location.path('/fuel');
           })
           .catch(function(response) {
               vm.errors = response.data;
           });
   }

});