angular.module('machine-list')
.config(function($routeProvider) {
    $routeProvider.when('/machines', {
        templateUrl: '/machine-list/machine-list.html',
        controller: 'MachineListController',
        controllerAs: 'vm'
    });
});