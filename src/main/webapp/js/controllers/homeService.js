/**
Service pour manipuler les homes.
*/

opowerApp.factory('homeFactory', function( $http, $rootScope ){
	var factory = {};
	// Retourne toutes les homes
	factory.getHomes = function() {
				return $http({
					method: 'GET',
					url: 'http://localhost:8080/rest/home'
				}).then(function successCallback(response) {
					  $rootScope.homes = response.data;
				}, function errorCallback(response) {
				})
		  };

	// Update d'une maison
	factory.update = function(home){
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/rest/home/',
			//headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : home
		});
	}

	// Ajout d'une maison
	factory.create = function(id,n,p,m){
		console.log("ajout d'une maison ...");
		$http({
			method : 'POST',
			url : 'http://localhost:8080/rest/person/'+id+'/',
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : 'nom='+n +'&taille='+p +'&nombrePiece='+m
		});
	}

	// Suppression d'une maison en fonction d'un id
	factory.delete = function(id){
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/rest/home/delete/'+id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
		});
	}

	return factory;
});
