angular.module('app.controllers', [])

.controller('oBPMobileCtrl', function($scope) {

})

.controller('signInCtrl', [
        '$state', '$scope', '$rootScope', '$http', 'GetBackEnd', // <-- controller dependencies
        function ($state, $scope, $rootScope, $http, GetBackEnd) {
          //*** begin controller code
          $scope.userData = {};
          $scope.loginUser = function() {
              var req = {
                method: 'POST',
                url: GetBackEnd.url + '/api/login',
                headers: {
                 'Content-Type': 'application/json'
                },
                data: $scope.userData
              }
              $http(req).then(
                function(response) { // Success
                  alert("Login Ok!");
                  //response.data is the entity. "data" is my array
                  $rootScope.news = response.data.data;
                  $state.go('tabsController.news', {});
                },
                function(response){ // Error
                  alert("Login error: " + response.status);
                });
              };
          //*** end controller code
}])

.controller('registerCtrl', [
          '$state', '$scope','$http', 'GetBackEnd',
          function($state, $scope, $http, GetBackEnd) {
          $scope.userData = {};
          $scope.register = function() {
              var req = {
                method: 'POST',
                url: GetBackEnd.url + '/api/create',
                headers: {
                 'Content-Type': 'application/json'
                },
                data: $scope.userData
              }
              $http(req).then(
                function(response) { // Success
                  alert("New user Ok!");
                  $state.go('signIn', {});
                },
                function(response){ // Error
                  if(response.status == 400) {
                    alert("User exists with this email");
                  }
                  else {
                    alert("Login error: " + response.status);
                  }
                });
          };
}])

.controller('newsCtrl', [
          '$state', '$scope', '$http', 'GetBackEnd',
          function($state, $scope, $http, GetBackEnd) {
}])

.controller('topRankCtrl', function($scope) {

})

.controller('contactCtrl', function($scope) {

})

.controller('cameraTabDefaultPageCtrl', function($scope) {

})

.controller('cartTabDefaultPageCtrl', function($scope) {

})

.controller('cloudTabDefaultPageCtrl', function($scope) {

})

