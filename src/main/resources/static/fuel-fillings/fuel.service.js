angular.module('fuel')
.service('fuelService', function($resource) {
   var service = this;

   var fuelResource = $resource('http://localhost:8080/fuel/:fuelId', {}, {
       query: {
           method: 'GET',
           isArray: false
       },

       fuelPrice: {
           method: 'GET',
           isArray: false,
           url: 'http://localhost:8080/fuel/price'
       },

       update: {
           method: 'PUT'
       }
   });

   service.search = function(params) {
       return fuelResource.query(params).$promise;
   };

   service.getFuelPrice = function() {
       return fuelResource.fuelPrice().$promise;
   };

   service.create = function(fuel) {
       return fuelResource.save({}, fuel).$promise;
   };

   service.remove = function(id) {
       return fuelResource.remove({
           fuelId : id
       }).$promise;
   };

   service.get = function(id) {
     return fuelResource.get({
         fuelId: id
     }).$promise
         .then(function(fuel) {
             fuel.date = new Date(fuel.date);
             return fuel;
         });
   };

   service.update = function(fuel) {
       return fuelResource.update({
           fuelId: fuel.id
       }, fuel).$promise;
   };
});