package hu.attilavegh.dressit.services.firebase

import hu.attilavegh.dressit.models.DressModel
import hu.attilavegh.dressit.models.UserModel
import io.reactivex.Observable

class FirebaseDataServiceMock: FirebaseDataServiceShape {
    override fun setUser(user: UserModel): Observable<UserModel> {
        return Observable.create { emitter ->
            emitter.onNext(user)
            emitter.onError(Throwable("Error setting user"))
        }
    }

    override fun getDresses(): Observable<List<DressModel>> {
        return Observable.create { emitter ->
            emitter.onNext(listOf(DressModel(), DressModel()))
            emitter.onError(Throwable("Error getting dresses"))
        }
    }
}