angular.module('app.services', [])
.service('GetBackEnd', [function(){
  return { 'initialize' : function() {
              Parse.initialize("<< your parse.com Application Id >>",
                    "<< your parse.com Javascript Id >>");
            }};
}]);

