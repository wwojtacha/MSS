angular.module('machine')
.service('machineService', function($resource) {
    var service = this;

    var machineResource = $resource('http://localhost:8080/machines/:machineId', {}, {
        query: {
            method: 'GET',
            isArray: false
        },
        update: {
            method: 'PUT'
        }
    });

    service.search = function(params) {
        return machineResource.query(params).$promise;
    };

    service.create = function(machine) {
        return machineResource.save({}, machine).$promise;
    };

    service.get = function(id) {
        return machineResource.get({
            machineId: id
        }).$promise;
    };

    service.update = function(machine) {
        return machineResource.update({
            machineId: machine.id
        }, machine).$promise;
    };
});