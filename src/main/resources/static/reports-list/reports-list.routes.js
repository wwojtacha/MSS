angular.module('reports-list')
.config(function($routeProvider){
    $routeProvider.when('fuel/averageConsumption', {
        templateUrl: '/reports-list/reports-list.html',
        controller: 'ReportsListController',
        controllerAs: 'vm'
    });
});