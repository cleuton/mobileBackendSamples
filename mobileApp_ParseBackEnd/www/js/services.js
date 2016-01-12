angular.module('app.services', [])
.service('GetBackEnd', [function(){
  return { 'initialize' : function() {
              Parse.initialize("mp4n9ZRGMUcgz4fvKQZvH6PNSkFei9TBejNKRtnM",
                    "6iyj4zdUxy8a0j6KvNyXislODX8hc3DSZT7JrZNK");
            }};
}]);

