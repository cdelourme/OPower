/**
Service pour manipuler les personnes.
*/

opowerApp.factory('heaterFactory', function( $http, $rootScope ){
	var factory = {};
	// Retourne tous les chauffages
	factory.getHeaters = function() {
				return $http({
					method: 'GET',
					url: 'http://localhost:8080/rest/heater'
				}).then(function successCallback(response) {
					  $rootScope.heaters = response.data;
				}, function errorCallback(response) {
				})
		  };

	// retourne une maison en fonction d'un id
	factory.searchHeater = function(id){
		console.log("Search ...");
		return $http({
			method : 'GET',
			url : 'http://localhost:8080/rest/heater/search/'+ id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
		}).then(function successCallback(response) {
			$rootScope.editheater = response.data;
		}, function errorCallback(response) {
			console.log("erreur du search()");
		});
	}

	// Update d'un heater
	factory.update = function(heater){
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/rest/heater/',
			//headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : heater
		});
	}

	// Ajout d'un chauffage
	factory.create = function(id,n,p){
		console.log("ajout d'un chauffage ...");
		$http({
			method : 'POST',
			url : 'http://localhost:8080/rest/home/'+id+'/heater/',
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : 'nom='+n +'&consoMoyenne='+p
		});
	}

	// Suppression d'un chauffage en fonction d'un id
	factory.delete = function(id){
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/rest/heater/delete/'+id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
		});
	}

	return factory;
});
