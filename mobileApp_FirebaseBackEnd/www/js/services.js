angular.module('app.services', [])
.service('GetBackEnd', [function(){
  return { 'url' :
    'https://<<your app url>>.firebaseio.com'};
}]);

