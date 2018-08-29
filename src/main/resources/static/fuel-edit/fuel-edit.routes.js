angular.module('fuel-edit')
.config(function($routeProvider) {
    $routeProvider.when('/fuel/edit/:fuelId', {
        templateUrl: '/fuel-edit/fuel-edit.html',
        controller: 'FuelEditController',
        controllerAs: 'vm',
        resolve: {
            fuel: function (fuelService, $route) {
                return fuelService.get($route.current.params.fuelId);
            }
        }
    });
});