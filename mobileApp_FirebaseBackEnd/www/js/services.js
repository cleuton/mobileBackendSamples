angular.module('app.services', [])
.service('GetBackEnd', [function(){
  return { 'url' :
    'https://radiant-torch-7260.firebaseio.com'};
}]);

