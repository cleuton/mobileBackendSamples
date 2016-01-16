# mobileBackendSamples
This is a mobile backend showroom, with some popular choices for mobile backend development, with lots of samples. It is based on my own experience with all these solutions. 

# The source code
I have provided an Ionic app, customized for each BackEnd type. Just de "www" part is stored, so, you have to create an ionic app and change the folder.

Let me describe what you'll find here: 

* dropbackend: Contains a [Dropwizard](http://www.dropwizard.io/0.9.1/docs/) RESTful service as a Mobile Backend. You can host it on AWS;
* GaeBackEnd: Contains a [Google App Engine](https://cloud.google.com/appengine/) Backend, based on Cloud Endpoints;
* mobileApp_DropBackEnd: An Ionic app that consumes the Dropwizard Backend;
* mobileApp_GaeBackEnd: An Ionic app that authenticates users using their Google Account, and get data from Google App Engine;
* mobileApp_ParseBackEnd: An Ionic app that authenticate users using "Username" and [Parse](http://parse.com) and gets data from it;
* 
