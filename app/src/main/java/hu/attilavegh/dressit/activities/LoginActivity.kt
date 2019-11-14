package hu.attilavegh.dressit.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import hu.attilavegh.dressit.R
import hu.attilavegh.dressit.utilities.ApplicationUtils

class LoginActivity : AppCompatActivity() {

    private val readPermission = listOf("email", "public_profile")
    private val authType = "rerequest"

    private lateinit var callbackManager: CallbackManager
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ApplicationUtils.createNotificationChannel(this)
        ApplicationUtils.checkPlayServices(this)

        this.supportActionBar?.hide()

        callbackManager = CallbackManager.Factory.create()
        auth = FirebaseAuth.getInstance()

        loadApp(auth.currentUser)

        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setPermissions(readPermission)
        loginButton.authType = authType

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                setResult(Activity.RESULT_OK)
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                setResult(Activity.RESULT_CANCELED)
            }

            override fun onError(e: FacebookException) {
                makeFailedAuthenticationAlert()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)

        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    loadApp(user)
                } else {
                    makeFailedAuthenticationAlert()
                    loadApp(null)
                }
            }
    }

    private fun makeFailedAuthenticationAlert() {
        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
    }

    private fun loadApp(user: FirebaseUser?) {
        if (user !== null) {
            val intent = Intent(this, TutorialActivity::class.java)
            this.startActivity(intent)
            finish()
        }
    }
}
