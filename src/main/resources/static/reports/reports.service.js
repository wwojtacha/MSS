angular.module('reports')
.service('reportsService', function($resource) {

    var service = this;

    var reportsResource = $resource('http://localhost:8080/fuel/averageConsumption/', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    });


    service.search = function() {
        return reportsResource.query().$promise;
    };

});