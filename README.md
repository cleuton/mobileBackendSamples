# mobileBackendSamples
This is a mobile backend showroom, with some popular choices for mobile backend development, with lots of samples. It is based on my own experience with all these solutions. 

# The source code
I have provided an Ionic app, customized for each BackEnd type. Just de "www" part is stored, so, you have to create an ionic app and change the folder.

Let me describe what you'll find here: 

* dropbackend: Contains a [Dropwizard](http://www.dropwizard.io/0.9.1/docs/) RESTful service as a Mobile Backend. You can host it on AWS;
* GaeBackEnd: Contains a [Google App Engine](https://cloud.google.com/appengine/) Backend, based on Cloud Endpoints;
* mobileApp_DropBackEnd: An [Ionic](http://ionicframework.com/) app that consumes the Dropwizard Backend;
* mobileApp_GaeBackEnd: An [Ionic](http://ionicframework.com/) app that authenticates users using their Google Account, and get data from Google App Engine;
* mobileApp_ParseBackEnd: An [Ionic](http://ionicframework.com/) app that authenticate users using "Username" and [Parse](http://parse.com) and gets data from it;
* mobileApp_FirebaseBackEnd: Contains an [Ionic](http://ionicframework.com/) which authenticate users using "email" and gets data from [Firebase](https://www.firebase.com/);

# Setup and compiling the apps
To generate the "dropbackend" Server, just use Maven to compile. To test it, just run the class "NewsFeed" as Java application.

To generate "GaeBackEnd", you need to install Google App Engine SDK and, if you want, the Google Eclipse Plugin. [Read this doc](https://cloud.google.com/appengine/docs/java/).

To generate the Ionic apps, [install Ionic](http://ionicframework.com/getting-started/) and create some Ionic apps, then, change the "www" folder.

To generate the "mobileApp_GaeBackEnd, you will need to install "cordova-plugin-googleplus. Read [this article](https://ionicthemes.com/tutorials/about/google-plus-login-with-ionic-framework) to know how to install it. 

 


