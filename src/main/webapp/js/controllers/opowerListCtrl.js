//'use strict';

// Contrôleur de la page List des utilisateurs
opowerApp.controller('listPersonCtrl', function($scope, $routeParams, personFactory){
        personFactory.getPersons();
    }
);

// Contrôleur de la page Edition d'un utilisateur
opowerApp.controller('editPersonCtrl', function($scope, $routeParams, homeFactory, personFactory, heaterFactory){
      //  console.log($routeParams.userId)
      $scope.showEditHome = false;
      $scope.showEditHeater = false;
      $scope.showEditPerson = false;

      $scope.nomHome = "";
    	$scope.surfaceHome = "";
    	$scope.nbPieceHome = "";

      $scope.createHome = function(){
        homeFactory.create($scope.edituser.id,$scope.nomHome,$scope.surfaceHome,$scope.nbPieceHome);
        $scope.$apply();
      }
      $scope.createHeater = function(){
        heaterFactory.create($scope.editHome.id,$scope.nomHeater,$scope.consoHeater);
      }
      $scope.updateHeater = function(){
        $scope.editheater.name = $scope.nomHeater ;
        $scope.editheater.consoMoyenne = $scope.consoHeater ;
        heaterFactory.update($scope.editheater);
      }
      $scope.deleteHeater = function(id){
        heaterFactory.delete(id);
      }
      $scope.modifyHeater = function(heater){
        $scope.nomHeater = heater.name;
        $scope.consoHeater = heater.consoMoyenne;
        $scope.editheater = heater;
        $scope.showEditHeater = !$scope.showEditHeater;

      }
      $scope.deleteHome = function(id){
        homeFactory.delete(id);
      }

      $scope.update = function(user){
        personFactory.update(user);
      }

      $scope.modifyShowEditHome = function(){
        $scope.showEditHome = !$scope.showEditHome;
      }
      $scope.modifyShowEditHeater = function(home){
        $scope.showEditHeater = !$scope.showEditHeater;
        $scope.editHome = home;
      }
      $scope.modifyShowEditPerson = function(){
        $scope.showEditPerson = !$scope.showEditPerson;
      }
    }
);
