import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';

admin.initializeApp();

exports.newDressNotification = functions.firestore.document('dresses/{dressId}')
    .onCreate((snap, context) => {
        console.log('Bus has been updated');

        const newDress = snap.data();

        if (newDress && !newDress.url) {
            return;
        }
    
        const payload = {
            data: {
                id: context.params.dressId,
                url: newDress && newDress.url,
            }
        };

        return admin.messaging().sendToTopic("new_dress", payload);
    });
