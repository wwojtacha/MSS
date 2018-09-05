angular.module('reports-list')
.controller('ReportsListController', function(reportsService) {

    var vm = this;

    vm.getAverageConsumption = getAverageConsumption;

    getAverageConsumption();

    vm.getMaxCounterStates = getMaxCounterStates;

    getMaxCounterStates();

    function getAverageConsumption() {
        reportsService.getAverageConsumption()
            .then(function(response) {
                vm.consumptions = response; //without "content". "content" for Page
            })
            .catch(function(response) {
                alert(response.data.message);
                vm.error = response.data.message;
            });
    }

    function getMaxCounterStates() {
        reportsService.getMaxCounterStates()
            .then(function(response) {
                vm.counterStates = response;
            })
            .catch(function(response) {
                vm.error = response.data.message;
            });
    }

});