angular.module('app.services', [])
.service('GetBackEnd', [function(){
  return {
      url : 'http://localhost:8080'
  }
}]);

