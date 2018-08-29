angular.module('reports-list')
.controller('ReportsListController', function(reportsService) {

    var vm = this;

    vm.search = search;

    search();

    function search() {
        reportsService.search()
            .then(function(response) {
                vm.consumptions = response.content;
            })
            .catch(function(response) {
                alert(response.data.message);
                vm.error = response.data.message;
            });
    }

});