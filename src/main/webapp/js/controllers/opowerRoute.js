'use strict';

/**
 * Configuration des routes
 */
opowerApp.config(['$routeProvider',
    function($routeProvider) {

        // Système de routage
        $routeProvider.when('/home', { templateUrl: 'views/home.html', controller: 'homeCtrl'});
        $routeProvider.when('/about', { templateUrl: 'views/about.html', controller: 'aboutCtrl'});
        $routeProvider.when('/createPerson', { templateUrl: 'views/createPerson2.html', controller: 'createPersonCtrl'});
        $routeProvider.when('/listPerson', { templateUrl: 'views/listPerson.html', controller: 'listPersonCtrl'});
        $routeProvider.when('/editPerson', { templateUrl: 'views/editPerson.html', controller: 'editPersonCtrl'});
        $routeProvider.when('/contact/', { templateUrl: 'views/contact.html', controller: 'contactCtrl'});
        $routeProvider.when('/login', { templateUrl: 'views/login.html', controller: 'loginCtrl'});
        $routeProvider.when('/person/:userId', {controller : 'detailPersonCtrl', templateUrl : 'views/editPerson.html'});
        //$routeProvider.otherwise({ redirectTo: '/home'});
    }
]);


/**
 * Définition des contrôleurs
 */
var opowerAppControllers = angular.module('opowerAppControllers', []);


// Contrôleur de la page d'accueil
opowerAppControllers.controller('homeCtrl', ['$scope',
    function($scope){
        $scope.message = "Bienvenue sur la page d'accueil";
        //console.log($routeParams)
    }
]);

// Contrôleur de la page de contact
opowerAppControllers.controller('contactCtrl', ['$scope','$routeParams',
    function($scope, $routeParams){
        $scope.message = "If you want to contact us do not hesitate to send us an email to contact@opower.com";
        // Si aucun paramètre n'est passé, on met notre phrase initiale
    }
]);

// Contrôleur de la page de contact
opowerAppControllers.controller('listPersonCtrl', ['$scope','$routeParams',
    function($scope, $routeParams){

    }
]);

// Contrôleur de la page de contact
opowerAppControllers.controller('createPersonCtrl', ['$scope','$routeParams',
    function($scope, $routeParams){

    }
]);

// Contrôleur de la page de contact
opowerAppControllers.controller('editPersonCtrl', ['$scope','$routeParams',
    function($scope, $routeParams){
      //  console.log($routeParams.userId)
    }
]);
