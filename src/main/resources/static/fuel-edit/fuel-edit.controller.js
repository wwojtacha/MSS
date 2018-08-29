angular.module('fuel-edit')
.controller('FuelEditController', function(fuel, fuelService, $location) {
    var vm = this;

    vm.fuel = fuel;

    vm.update = update;

    function update() {
        fuelService.update(vm.fuel)
            .then(function() {
                $location.path('/fuel');
                })
            .catch(function(response) {
                vm.errors = response.data;
            });
    }

});