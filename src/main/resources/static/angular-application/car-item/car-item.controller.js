/**
 * Created by Anton Hubarevich on 24.04.2017.
 */
'use strict';

/** Registers `carItem` component, along with its associated controller and template*/
angular.
module('carItem').
component('carItem', {
    templateUrl: '/angular-application/car-item/car-item.template.html',
    controller: ['$http', '$scope', '$routeParams', 'localStorageService',
        function NewsItemController($http, $scope, $routeParams) {
                var baseUrl = "http://localhost:8080/";
                $http
                    .get(baseUrl + "api/cars/" + $routeParams.carId)
                    .then(function(response){
                        $scope.car = response.data;
                    });
        }
    ]
});