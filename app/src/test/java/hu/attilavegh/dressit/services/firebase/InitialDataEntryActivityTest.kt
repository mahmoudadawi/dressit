package hu.attilavegh.dressit.services.firebase

import hu.attilavegh.dressit.models.UserModel
import org.junit.Test
import org.mockito.Mockito

class FirebaseDataServiceTest {

    @Test
    fun testSetUserFirebaseCall() {
        val serviceSpy = Mockito.spy(FirebaseDataServiceMock())
        val mockUser = UserModel()

        serviceSpy.setUser(mockUser)

        Mockito.verify(serviceSpy).setUser(mockUser)
    }

    @Test
    fun testGetDressesFirebaseCall() {
        val serviceSpy = Mockito.spy(FirebaseDataServiceMock())

        serviceSpy.getDresses()

        Mockito.verify(serviceSpy).getDresses()
    }
}