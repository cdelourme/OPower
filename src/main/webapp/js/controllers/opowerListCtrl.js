//'use strict';

// Contrôleur de la page List des utilisateurs
opowerApp.controller('listPersonCtrl', function($scope, $routeParams, personFactory){
        personFactory.getPersons();
    }
);

// Contrôleur de la page Edition d'un utilisateur
opowerApp.controller('editPersonCtrl', function($scope, $routeParams, homeFactory, personFactory, heaterFactory, deviceFactory){
      //  console.log($routeParams.userId)
      // il faudra afficher cette page en fonction de l'id de la personne

      $scope.showEditHome = false;
      $scope.showEditHeater = false;
      $scope.showEditPerson = false;
      $scope.showEditDevice = false;

      $scope.nomHome = "";
    	$scope.surfaceHome = "";
    	$scope.nbPieceHome = "";

      //      ***********************
      //      ***     PERSON      ***
      //      ***********************
      $scope.update = function(user){
        personFactory.update(user);
      }
      $scope.modifyShowEditPerson = function(){
        $scope.showEditPerson = !$scope.showEditPerson;
      }

      //      ***********************
      //      ***     HOME        ***
      //      ***********************
      $scope.createHome = function(){
        homeFactory.create($scope.edituser.id,$scope.nomHome,$scope.surfaceHome,$scope.nbPieceHome);
        $scope.$apply();
      }
      $scope.deleteHome = function(id){
        homeFactory.delete(id);
      }
      $scope.modifyShowEditHome = function(){
        $scope.showEditHome = !$scope.showEditHome;
      }

      //      ***********************
      //      ***     HEATER      ***
      //      ***********************
      $scope.createHeater = function(){
        heaterFactory.create($scope.editHome.id,$scope.nomHeater,$scope.consoHeater);
      }
      $scope.updateHeater = function(){
        $scope.editheater.name = $scope.nomHeater ;
        $scope.editheater.consoMoyenne = $scope.consoHeater ;
        heaterFactory.update($scope.editheater);
      }
      $scope.modifyShowEditHeater = function(home,heater){
        $scope.showEditHeater = !$scope.showEditHeater;
        $scope.editHome = home;
        if (heater != null){
          $scope.nomHeater = heater.name;
          $scope.consoHeater = heater.consoMoyenne;
          $scope.editheater = heater;
        }
        else{
          $scope.nomHeater = "";
          $scope.consoHeater = "";
        }
      }
//      $scope.modifyShowEditHeater = function(){
//        $scope.showEditHeater = !$scope.showEditHeater;
//      }

      //      *****************************
      //      ***   ELECTRONIC DEVICE   ***
      //      *****************************
      $scope.deleteHeater = function(id){
        heaterFactory.delete(id);
      }
      $scope.createDevice = function(){
        deviceFactory.create($scope.editHome.id,$scope.nomDevice,$scope.consoDevice);
      }
      $scope.updateDevice = function(){
        $scope.editdevice.name = $scope.nomDevice ;
        $scope.editdevice.consoMoyenne = $scope.consoDevice ;
        deviceFactory.update($scope.editdevice);
      }
      $scope.deleteDevice = function(id){
        deviceFactory.delete(id);
      }
      $scope.modifyShowEditDevice = function(home,device){
        $scope.showEditDevice = !$scope.showEditDevice;
        $scope.editHome = home;
        if (device != null){
          $scope.nomDevice = device.name;
          $scope.consoDevice = device.consoMoyenne;
          $scope.editdevice = device;
        }
        else{
          $scope.nomDevice = "";
          $scope.consoDevice = "";
        }
      }
//      $scope.modifyShowEditDevice = function(){
//        $scope.showEditDevice = !$scope.showEditDevice;
//      }
//      $scope.modifyHeater = function(heater){
//        $scope.nomHeater = heater.name;
//        $scope.consoHeater = heater.consoMoyenne;
//        $scope.editheater = heater;
//        $scope.showEditHeater = !$scope.showEditHeater;
//      }

//      $scope.modifyShowEditHeater = function(home){
//        $scope.showEditHeater = !$scope.showEditHeater;
//        $scope.editHome = home;
//      }

    }
);
