package hu.attilavegh.dressit.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import hu.attilavegh.dressit.LoginActivity
import hu.attilavegh.dressit.R
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileViewModel = ViewModelProviders
            .of(this)
            .get(ProfileViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        createItemListeners(view)
        setProfileData(view)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.logout_button -> onLogout()
        }
    }

    private fun createItemListeners(view: View) {
        val items: IntArray = intArrayOf(
            R.id.logout_button
        )

        for (item in items) {
            view.findViewById<AppCompatButton>(item).setOnClickListener(this)
        }
    }

    private fun onLogout() {
        LoginManager.getInstance().logOut()
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(context, LoginActivity::class.java)
        this.startActivity(intent)
    }

    private fun setProfileData(view: View) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            setProfilePicture(view, it)

            view.name_field.text = it.displayName
        }
    }

    private fun setProfilePicture(view: View, user: FirebaseUser) {
        Picasso.with(context)
            .load(user.photoUrl.toString() + "?width=512&height=512")
            .placeholder(R.drawable.default_profile_picture)
            .error(R.drawable.default_profile_picture)
            .fit()
            .into(view.profile_picture)
    }
}