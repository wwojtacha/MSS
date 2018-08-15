angular.module('machine-list')
.controller('MachineListController', function(machineService, $location) {
    var vm = this;

    vm.params = {};

   vm.search = search;

   vm.edit = edit;

   search();

   function search() {
       machineService.search(vm.params)
           .then(function(response) {
               vm.machines = response.content;
           })
           .catch(function(response) {
               alert(response.data.message);
               vm.error = response.data.message;
       });
   }

    function edit(id) {
        $location.path('/machines/edit/' + id);
    }

});