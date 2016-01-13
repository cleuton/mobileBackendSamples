angular.module('app.controllers', [])

.controller('oBPMobileCtrl', function($scope) {

})

.controller('signInCtrl', [
        '$state', '$scope', '$rootScope', '$http', 'GetBackEnd', // <-- controller dependencies
        function ($state, $scope, $rootScope, $http, GetBackEnd) {
          //*** begin controller code
          $scope.userData = {};

          /*
            As Firebase does not store username, we decided to remove the
            "register.html" template, and join both controllers here.
          */

          $scope.loginUser = function() {
            var ref = new Firebase(GetBackEnd.url);
            ref.authWithPassword({
              email    : $scope.userData.email,
              password : $scope.userData.password
            }, function(error, authData) {
              if (error) {
                alert("Login Failed!", error);
              } else {
                alert("Authenticated successfully with payload:", authData);
                $state.go('tabsController.news', {});
              }
            });
          };

          $scope.register = function() {
            var ref = new Firebase(GetBackEnd.url);
            ref.createUser({
              email    : $scope.userData.email,
              password : $scope.userData.password
            }, function(error, user) {
              if (error) {
                alert("Error creating user:", error);
              } else {
                alert("Successfully created user account with uid:", user.uid);
                $scope.loginUser();
              }
            });
          };

          //*** end controller code
}])


.controller('newsCtrl', [
          '$state', '$scope','$http', 'GetBackEnd',
          function($state, $scope, $http, GetBackEnd) {
            var ref = new Firebase(GetBackEnd.url);
            $scope.news = [];
            ref.on("value", function(snapshot) {
              var newsArray = snapshot.val().news;
              for (var i=0; i<newsArray.length; i++) {
                  var newsLine = {};
                  newsLine.newsDate = newsArray[i].newsDate;
                  newsLine.newsHeadLine = newsArray[i].newsHeadLine;
                  $scope.news.push(newsLine);
                }
                $scope.$apply();

            }, function (errorObject) {
              console.log("The read failed: " + errorObject.code);
            });
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

