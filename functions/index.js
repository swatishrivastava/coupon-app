const functions= require('firebase-functions');
const admin=require('firebase-admin');
admin.initializeApp(functions.config().firebase);   

exports.pushNotification=functions.database.ref('/Deals/{pushId}').onWrite(event =>{


     console.log('Push notification event triggered');

     var valueObj=event.data.val();

     const payload={

notification:{
    title: 'test notification',
    body: 'test notify',
    sound: "default"
},

     };


       const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


 return admin.messaging().sendToTopic("pushNotifications", payload, options);
});
