angular.module('machine-edit')
.controller('MachineEditController', function(machine, machineService, $location) {
   var vm = this;

   vm.machine = machine;

   vm.update = update;

   function update() {
       machineService.update(vm.machine)
           .then(function() {
               $location.path('/machines/');
           })
           .catch(function(response) {
               vm.errors = response.data;
           });
   }
});