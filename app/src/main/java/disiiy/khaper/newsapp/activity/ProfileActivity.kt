package disiiy.khaper.newsapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import disiiy.khaper.newsapp.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        fun getLaunchService (from: Context) = Intent(from, ProfileActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()
        btn_logout_profile.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_logout_profile -> logout()
        }
    }

    private fun logout() {
        startActivity(Intent(SignInActivity.getLaunchService(this)))
        FirebaseAuth.getInstance().signOut()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(MainActivity.getLaunchService(this)))
    }
}