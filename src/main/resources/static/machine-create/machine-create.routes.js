angular.module('machine-create')
.config(function($routeProvider) {
    $routeProvider.when('/machines/add', {
        templateUrl: '/machine-create/machine-create.html',
        controller: 'MachineCreateController',
        controllerAs: 'vm'
    });
});