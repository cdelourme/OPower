
opowerApp.controller('mainCtrl', function($scope,$http,$routeParams,personFactory){

	$scope.deleteUser = function(id){
		personFactory.deleteUser(id);
	}

	$scope.searchPerson = function(id){
		personFactory.searchPerson(id);
	}
})



opowerApp.controller('homeCtrl', function($scope,$http,$routeParams,personFactory){
		personFactory.getPersons();
})


opowerApp.controller('createPersonCtrl', function($scope,$http,$routeParams,personFactory){
	$scope.nom = "";
	$scope.prenom = "";
	$scope.mail = "";

	$scope.createUser = function(){
		personFactory.create($scope.nom, $scope.prenom, $scope.mail);
	}
})
