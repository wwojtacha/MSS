angular.module('fuel-create')
.config(function($routeProvider) {
    $routeProvider.when('/fuel/add', {
        templateUrl: '/fuel-create/fuel-create.html',
        controller: 'FuelCreateController',
        controllerAs: 'vm'
    });
});