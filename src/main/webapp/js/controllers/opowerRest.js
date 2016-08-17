opowerApp.controller("UserCtrl", function ($scope, $http) {
  $scope.loadData = function () {
    $http.get("http://localhost:8080/rest/person").success(function (data) {      $scope.persons = data;    });
  }

  $scope.createPerson = function () {
    $http.get("htt://localhost:8080/rest/person").success(function (data) {      $scope.persons = data;    });
  }

    $scope.loadDataPromise = function () {
        $http.get("data.json").then(function (response) {
            console.log("Status: " + response.status);
            console.log("Type: " + response.headers("content-type"));
            console.log("Length: " + response.headers("content-length"));
            $scope.persons = response.data;
        });
    }
});
