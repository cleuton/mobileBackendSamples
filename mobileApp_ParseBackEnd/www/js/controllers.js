angular.module('app.controllers', [])

.controller('oBPMobileCtrl', function($scope) {

})

.controller('signInCtrl', [
        '$state', '$scope', '$rootScope', '$http', 'GetBackEnd', // <-- controller dependencies
        function ($state, $scope, $rootScope, $http, GetBackEnd) {
          //*** begin controller code
          $scope.userData = {};
          $scope.loginUser = function() {
                GetBackEnd.initialize();
                Parse.User.logIn($scope.userData.username, $scope.userData.password, {
                  success: function(user) {
                    $state.go('tabsController.news', {});
                  },
                  error: function(user, error) {
                    alert("User login error: " + error.code);
                  }
                });
              };
          //*** end controller code
}])

.controller('registerCtrl', [
          '$state', '$scope','$http', 'GetBackEnd',
          function($state, $scope, $http, GetBackEnd) {
            $scope.userData = {};

            $scope.register = function() {
              GetBackEnd.initialize();
              var user = new Parse.User();
              user.set("username", $scope.userData.username);
              user.set("password", $scope.userData.password);
              user.set("email",    $scope.userData.email);

              user.signUp(null, {
                success: function(user) {
                          $scope.user = user;
                          alert("Success Creating User Account ");
                          $state.go('signIn', {});

                },
                error: function(user, error) {
                  alert("Error signing up: " + error.code + " " + error.message);
                }
            });
          }
}])

.controller('newsCtrl', [
          '$state', '$scope','$http', 'GetBackEnd',
          function($state, $scope, $http, GetBackEnd) {
            GetBackEnd.initialize();
            var query = new Parse.Query("news");
            $scope.news = [];
            query.find({
              success: function(news) {
                for (var i=0; i<news.length; i++) {
                  var newsLine = {};
                  newsLine.newsDate = news[i].get("newsDate");
                  newsLine.newsHeadLine = news[i].get("newsHeadLine");
                  $scope.news.push(newsLine);
                }
                // This is asynchronous code, we need to refresh the view:
                $scope.$apply();
              },
              error: function(news, error) {
                alert("Error getting news: " + error.message);
              }
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

