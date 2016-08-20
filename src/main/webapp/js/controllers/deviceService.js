/**
Service pour manipuler les devices.
*/

opowerApp.factory('deviceFactory', function( $http, $rootScope ){
	var factory = {};
	// Retourne tous les devices
	factory.getDevices = function() {
				return $http({
					method: 'GET',
					url: 'http://localhost:8080/rest/device'
				}).then(function successCallback(response) {
					  $rootScope.devices = response.data;
				}, function errorCallback(response) {
				})
		  };

	// retourne un device en fonction d'un id
	factory.searchDevice = function(id){
		return $http({
			method : 'GET',
			url : 'http://localhost:8080/rest/device/search/'+ id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
		}).then(function successCallback(response) {
			$rootScope.editdevice = response.data;
		}, function errorCallback(response) {
			console.log("erreur du search()");
		});
	}

	// Update d'un device
	factory.update = function(device){
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/rest/device/',
			//headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : device
		});
	}

	// Ajout d'un device
	factory.create = function(id,n,p){
		console.log("ajout d'un device ...:" + id + "," + n + "," + p);
		$http({
			method : 'POST',
			url : 'http://localhost:8080/rest/home/'+id+'/Device/',
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : 'nom='+n +'&consoMoyenne='+p
		});
	}

	// Suppression d'un device en fonction d'un id
	factory.delete = function(id){
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/rest/device/delete/'+id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
		});
	}

	return factory;
});
