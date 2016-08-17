
var userFactory = function($resource){
	return $resource('http://localhost:8080/rest/person/:slug', { slug : '@slug' },
		{
			'update' : { method:'PUT' },
		});
};

opowerApp.factory('Person', [ '$resource', userFactory ]);

var user = opowerApp.controller('UserCtrl', ['$scope', 'Person', function($scope, Person) {
$scope.persons = {};
$scope.idEditedUser;

//Perform "GET http://mydomain.com/api/user/"
Person.get().$promise.then(function(data) {
      $scope.persons = data;
			console.log($scope.persons);
    }//, function(error){/*Error treatment*/};
  );
//}]);

//Perform "DELETE http://mydomain.com/api/user/:slug"
	 $scope.deleteUser = function(user) {
	 console.log( user );
	 console.log(user.slug);
   Person.delete("delete/" + user.id);
};

//Perform "POST http://mydomain.com/api/user/:slug"
var createUser = function(user) {
   Person.save(user);
};

//Perform "PUT http://mydomain.com/api/user/:slug"
var editUserR = function(user) {
   Person.update(user);
};
}]);



opowerApp.controller('detailPersonCtrl', function($scope,$http,$routeParams){
	console.log($routeParams.userId)
	$scope.edituser;
	$scope.searchPerson = function(user){
		console.log("Search ...");
		return $http({
			method : 'GET',
			url : 'http://localhost:8080/rest/person/search/'+ user.id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
//			data : 'mail='+$scope.mail +'&nom='+$scope.nom +'&prenom='+$scope.prenom
		});
//		$scope.message = "Welcome into the race " + $scope.prenom + ", " + $scope.nom + " :)";
	}
})

opowerApp.controller('addPersonCtrl', function($scope,$http){
	$scope.message = "";
	$scope.create = function(){
		$http({
			method : 'POST',
			url : 'http://localhost:8080/rest/person/',
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
			data : 'mail='+$scope.mail +'&nom='+$scope.nom +'&prenom='+$scope.prenom
		});
		$scope.message = "Welcome into the race " + $scope.prenom + ", " + $scope.nom + " :)";
	}
})

opowerApp.controller('deletePersonCtrl', function($scope,$http){
	$scope.deleteUser = function(id){
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/rest/person/delete/'+id,
			headers : {'Content-Type':'application/x-www-form-urlencoded'},
			//data : 'mail='+$scope.mail +'&nom='+$scope.nom +'&prenom='+$scope.prenom
		});
		//$scope.message = "Welcome into the race " + $scope.prenom + ", " + $scope.nom + " :)";
	}
})
