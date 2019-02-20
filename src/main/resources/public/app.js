 var app_firebase = {};
 (function(){
 
 // Initialize Firebase
 var config = {
    apiKey: "AIzaSyDXY6BDM6UVrVW5QM8yyiYcktrdwBipYBw",
    authDomain: "patahouse-906d2.firebaseapp.com",
    databaseURL: "https://patahouse-906d2.firebaseio.com",
    projectId: "patahouse-906d2",
    storageBucket: "patahouse-906d2.appspot.com",
    messagingSenderId: "658436442361"
  };
  firebase.initializeApp(config);

  app_firebase = firebase;

})()