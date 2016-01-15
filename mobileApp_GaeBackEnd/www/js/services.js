angular.module('app.services', [])
.service('UserService', function() {
	// For the purpose of this example I will store user data on ionic local storage but you should save it on a database

  var setUser = function(user_data) {
    window.localStorage.starter_google_user = JSON.stringify(user_data);
  };

  var getUser = function(){
    return JSON.parse(window.localStorage.starter_google_user || '{}');
  };

  return {
    getUser: getUser,
    setUser: setUser
  };
})
.service('GetBackEnd', [function(){
  return {
      'url': 'http://localhost:8888/_ah/api/gaebackend/v1/newsarticle',
      'authScopes' : 'https://www.googleapis.com/auth/userinfo.email',
    	'cliendId' :'883135823153-21o3rj4jcegbdanbjs0hd68rfbe17m81.apps.googleusercontent.com'
    };
}]);

