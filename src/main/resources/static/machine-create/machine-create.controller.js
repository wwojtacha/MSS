angular.module('machine-create')
.controller('MachineCreateController', function(machineService, $location) {
    var vm = this;

    vm.machine = {};

    vm.create = create;

    function create() {
        machineService.create(vm.machine)
            .then(function() {
                $location.path('/machines');
            })
            .catch(function(response) {
                vm.errors = response.data
            });
    }
});