/**
Service pour manipuler les personnes.
*/

opowerApp.factory('personFactory', function( $http, $rootScope ){
	var factory = {};
	// Retourne toutes les personnes
	factory.getPersons = function() {
		$rootScope.persons=[];
				return $http({
					method: 'GET',
					url: 'http://localhost:8080/rest/person'
				}).then(function successCallback(response) {
						console.log(response.data);
						$rootScope.persons = response.data;
						console.log(typeof $rootScope.persons);

				}, function errorCallback(response) {
				})
		  };

	// retourne une personne en fonction d'un id
	factory.searchPerson = function(id){
		console.log("Search ...");
		return $http({
			method : 'GET',
			url : 'http://localhost:8080/rest/person/search/'+ id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
		}).then(function successCallback(response) {
			$rootScope.edituser = response.data;
		}, function errorCallback(response) {
			console.log("erreur du search()");
		});
	}

	// Update d'une personne
	factory.update = function(user){
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/rest/person/',
			//headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : user
		});
	}

	// Ajout d'une personne
	factory.create = function(n,p,m){
		$http({
			method : 'POST',
			url : 'http://localhost:8080/rest/person/',
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : 'mail='+m +'&nom='+n +'&prenom='+p
		});
	}

	// Suppression d'une personne en fonction d'un id
	factory.deleteUser = function(id){
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/rest/person/delete/'+id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
		});
	}

	return factory;
});
