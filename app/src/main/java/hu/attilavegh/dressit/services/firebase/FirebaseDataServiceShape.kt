package hu.attilavegh.dressit.services.firebase

import hu.attilavegh.dressit.models.DressModel
import hu.attilavegh.dressit.models.UserModel
import io.reactivex.Observable

interface FirebaseDataServiceShape {
    fun setUser(user: UserModel): Observable<UserModel>
    fun getDresses(): Observable<List<DressModel>>
}