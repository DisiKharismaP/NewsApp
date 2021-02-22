package disiiy.khaper.newsapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import disiiy.khaper.newsapp.R
import disiiy.khaper.newsapp.model.Users
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId : String = " "

    companion object{
        fun getLaunchService (from: Context) = Intent(from, SignUpActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        ib_back_signup.setOnClickListener(this)
        btn_sign_up.setOnClickListener(this)
        tv_login.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ib_back_signup -> startActivity(Intent(SignInActivity.getLaunchService(this)))
            R.id.btn_sign_up -> signUpUser()
            R.id.tv_login -> startActivity(SignInActivity.getLaunchService(this))
        }
    }

    private fun signUpUser() {
        val fullName : String = et_fullname.text.toString()
        val email : String = et_email_signup.text.toString()
        val password : String = et_password_signup.text.toString()
        val confirmpassword : String = et_confirm_password_signup.text.toString()
        if(fullName == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if(email == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if(password == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if((confirmpassword == "").equals(password)){
            Toast.makeText(this, getString(R.string.txt_error_password), Toast.LENGTH_SHORT).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
                if(it.isSuccessful){
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child(getString(R.string.txt_user)).child(firebaseUserId)
                    val userHashMap = HashMap <String, Any>()

                    userHashMap["uid"] = firebaseUserId
                    userHashMap["fullName"] = fullName
                    userHashMap["email"] = email
                    userHashMap["linkedIn"] = ""
                    userHashMap["fullname"] = ""
                    userHashMap["instagram"] = ""
                    userHashMap["medium"] = ""
                    userHashMap["photo"] = ""

                    refUsers.updateChildren(userHashMap).addOnCompleteListener { it ->
                        if(it.isSuccessful){
                            startActivity(Intent(MainActivity.getLaunchService(this)))
                            finish()
                        }
                    }
                }else{
                    Toast.makeText(this, getString(R.string.txt_error_register)+ it.exception!!
                        .message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}