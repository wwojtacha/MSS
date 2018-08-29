angular.module('fuel-list')
.config(function($routeProvider){
    $routeProvider.when('/fuel', {
            templateUrl: '/fuel-list/fuel-list.html',
            controller: 'FuelListController',
            controllerAs: 'vm',
            resolve: {
                fuelPrice: function(fuelService) {
                    return fuelService.getFuelPrice()
                        .then(function(response) {
                            return response.fuelPrice;
                        });
                }
            }
    });
});