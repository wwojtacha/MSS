angular.module('fuel-list')
.controller('FuelListController', function(fuelService, fuelPrice, $location) {

    var vm = this;

    vm.params = {};

    vm.search = search;

    vm.edit = edit;

    vm.remove = remove;

    vm.fuelPrice = fuelPrice;

    search();

    function search() {
        var dates = getDateRange();
        vm.params.startDate = dates.startDate;
        vm.params.endDate = dates.endDate;
        fuelService.search(vm.params)
            .then(function(response) {
                vm.fillings = response.content;
            })
            .catch(function(response) {
                alert(response.data.message);
                vm.error = response.data.message;
            });

    }

    function remove(fuelId) {
        fuelService.remove(fuelId)
            .then(function() {
                search();
            });
    }

    function edit(id) {
        $location.path('/fuel/edit/' + id);
    }

    function getDateRange() {
        var startYear, startMonth, startDay,
            endYear, endMonth, endDay;

        if (vm.year) {
            startYear = vm.year;
            endYear = vm.year;
        } else {
            startYear = 1900;
            endYear = 2999;
        }

        if (vm.month) {
            startMonth = vm.month - 1;
            endMonth = vm.month - 1;
        } else {
            startMonth = 0;
            endMonth = 11;
        }

        if (vm.day) {
            startDay = vm.day;
            endDay = vm.day;
        } else {
            startDay = 1;
            endDay = new Date(endYear, endMonth + 1, 0).getDate();
        }

        return {
            startDate: new Date(startYear, startMonth, startDay, 1, 59, 59, 999),
            endDate: new Date(endYear, endMonth, endDay, 25, 59, 59, 999)
        };
    }

});