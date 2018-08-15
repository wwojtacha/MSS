    angular.module('machine-edit')
.config(function($routeProvider) {
    $routeProvider.when('/machines/edit/:machineId', {
        templateUrl: '/machine-edit/machine-edit.html',
        controller: 'MachineEditController',
        controllerAs: 'vm',
        resolve: {
            machine: function (machineService, $route) {
                return machineService.get($route.current.params.machineId);
            }
        }
    });
});
