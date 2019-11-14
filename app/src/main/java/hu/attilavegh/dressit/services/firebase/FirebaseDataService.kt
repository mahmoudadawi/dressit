package hu.attilavegh.dressit.services.firebase

import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import hu.attilavegh.dressit.models.DressModel
import hu.attilavegh.dressit.models.UserModel
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class FirebaseDataService @Inject constructor(): FirebaseDataServiceShape {

    private var database: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun setUser(user: UserModel): Observable<UserModel> {
        return Observable.create { emitter ->
            database.collection("users").document(user.id)
                .set(user.getUserHashMap())
                .addOnSuccessListener {  emitter.onNext(user) }
                .addOnFailureListener { e -> emitter.onError(e) }
        }
    }

    override fun getDresses(): Observable<List<DressModel>> {
        return Observable.create { emitter ->
            val dressListSnapshotListenerRegistration = database.collection("dresses")
                .addSnapshotListener(EventListener<QuerySnapshot> { dresses, error ->
                    if (error != null) {
                        emitter.onError(error)
                        return@EventListener
                    }

                    val parsedDresses = ArrayList<DressModel>()
                    for (dress in dresses!!) {
                        val name = dress.data["name"] as String
                        val url = dress.data["url"] as String

                        parsedDresses.add(DressModel(name, url))
                    }

                    emitter.onNext(parsedDresses)
                })

            emitter.setCancellable { dressListSnapshotListenerRegistration.remove() }
        }
    }
}