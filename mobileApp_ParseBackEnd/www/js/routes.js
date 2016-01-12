angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider



    .state('oBPMobile', {
      url: '/page1',
      templateUrl: 'templates/oBPMobile.html',
      controller: 'oBPMobileCtrl'
    })





    .state('signIn', {
      url: '/page2',
      templateUrl: 'templates/signIn.html',
      controller: 'signInCtrl'
    })





    .state('register', {
      url: '/page3',
      templateUrl: 'templates/register.html',
      controller: 'registerCtrl'
    })





    .state('tabsController.news', {
      url: '/page5',
      views: {
        'tab1': {
          templateUrl: 'templates/news.html',
          controller: 'newsCtrl'
        }
      }
    })





    .state('tabsController.topRank', {
      url: '/page6',
      views: {
        'tab2': {
          templateUrl: 'templates/topRank.html',
          controller: 'topRankCtrl'
        }
      }
    })





    .state('tabsController.contact', {
      url: '/page7',
      views: {
        'tab3': {
          templateUrl: 'templates/contact.html',
          controller: 'contactCtrl'
        }
      }
    })




    .state('tabsController', {
      url: '/page4',
      abstract:true,
      templateUrl: 'templates/tabsController.html'
    })

    ;

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/page1');

});
