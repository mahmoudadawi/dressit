import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';

admin.initializeApp();

exports.newDressNotification = functions.firestore.document('dresses/{dressId}')
    .onCreate((snap, context) => {
        console.log('A new dress is added');

        const newDress = snap.data();

        if (newDress && !newDress.url) {
            return;
        }

        console.log(newDress);
    
        const payload = {
            data: {
                id: context.params.dressId,
                url: newDress && newDress.url,
            }
        };

        return admin.messaging().sendToTopic("new_dress", payload);
    });
