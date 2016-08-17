//'use strict';

/**
 * Déclaration de l'application routeApp
 */
var opowerApp = angular.module('opowerApp', [
    // Dépendances du "module"
    'ngRoute',
    'ngResource'
]);

/**
 * Configuration des routes
 */
opowerApp.config(['$routeProvider',
    function($routeProvider) {
        // Système de routage
        $routeProvider.when('/home', { templateUrl: 'views/home.html', controller: 'homeCtrl'});
        $routeProvider.when('/about', { templateUrl: 'views/about.html', controller: 'mainCtrl'});
        $routeProvider.when('/createPerson', { templateUrl: 'views/createPerson.html', controller: 'createPersonCtrl'});
        $routeProvider.when('/listPerson', { templateUrl: 'views/listPerson.html', controller: 'listPersonCtrl'});
        $routeProvider.when('/editPerson', { templateUrl: 'views/editPerson.html', controller: 'mainCtrl'});
        $routeProvider.when('/contact/', { templateUrl: 'views/contact.html', controller: 'mainCtrl'});
        $routeProvider.when('/login', { templateUrl: 'views/login.html', controller: 'mainCtrl'});
        $routeProvider.when('/person/:userId', {controller : 'editPersonCtrl', templateUrl : 'views/editPerson.html'});
        $routeProvider.otherwise({ redirectTo: '/home'});
    }
]);
