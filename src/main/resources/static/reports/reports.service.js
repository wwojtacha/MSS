angular.module('reports')
.service('reportsService', function($resource) {

    var service = this;

    var reportsResource = $resource('http://localhost:8080/fuel/averageConsumption', {}, {
        getAverageConsumption: {
            method: 'GET',
            isArray: true
        },
        counterStates: {
            method: 'GET',
            isArray: true,
            url: 'http://localhost:8080/fuel/counters'
        }
    });


    service.getAverageConsumption = function() {
        return reportsResource.query().$promise;
    };

    service.getMaxCounterStates = function() {
        return reportsResource.counterStates().$promise;
    };

});