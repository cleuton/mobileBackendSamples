/*
{
  "items" : [ {
    "newsDate" : "02/01/2016 7:21",
    "newsHeadLine" : "Breaking news #1"
  } ]
}
*/

// This is using GAE Local Development Server.
var bk = {
      'url': 'http://localhost:8888/_ah/api/gaebackend/v1/newsarticle',
    };


function getArticles($scope, $http, accToken) {
      var req = {
        method: 'GET',
        url: bk.url,
        headers: {
         'Content-Type': 'application/json',
         'Authorization': 'Bearer ' + accToken
        }
      }
    $http(req).then(
      function(response) {
          if(response.status == 200) {
            $scope.news = response.data.items;
            $scope.$apply();
          }
          else {
            alert("Error listing news: " + response.status);
          }

      });
}

angular.module('app.controllers', [])

.controller('oBPMobileCtrl', ['$state', '$scope', function($state, $scope) {
    $scope.lista = function() {
      $state.go('tabsController.news', {});
    }
}])


/*
{"items":[{"newsDate":"02/01/2016 7:21","newsHeadLine":"Breaking news #1"}],"result":{"items":[{"newsDate":"02/01/2016 7:21","newsHeadLine":"Breaking news #1"}]}}
*/

.controller('newsCtrl', [
          '$state', '$scope','$http', 'UserService', '$ionicLoading',
          function($state, $scope, $http, UserService, $ionicLoading) {

              $ionicLoading.show({
                template: 'Logging in...'
              });

              window.plugins.googleplus.login(
                {},
                function (user_data) {
                  UserService.setUser({
                    userID: user_data.userId,
                    name: user_data.displayName,
                    email: user_data.email,
                    picture: user_data.imageUrl,
                    accessToken: user_data.oauthToken,
                    idToken: user_data.idToken
                  });

                  $ionicLoading.hide();
                  getArticles($scope, $http, UserService.accessToken);
                },
                function (msg) {
                  $ionicLoading.hide();
                }
              );

			$scope.logout =  function(){
				$ionicLoading.show({
					template: 'Logging out...'
				});
				//google logout
				window.plugins.googleplus.logout(
					function (msg) {
            alert(msg);
						$ionicLoading.hide();
						$state.go('oBPMobile');
					},
					function(fail){
						console.log(fail);
					}
				);
			}

}])

.controller('topRankCtrl', function($scope) {

})

.controller('contactCtrl', function($scope) {

})


