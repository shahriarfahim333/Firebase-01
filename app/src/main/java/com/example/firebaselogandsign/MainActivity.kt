package com.example.firebaselogandsign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaselogandsign.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.text.setOnClickListener {
            startActivity(Intent(this,LogActivity::class.java))
        }

        binding.bt.setOnClickListener {
            if (binding.email.text.toString().isEmpty()||binding.pass.text.toString().isEmpty()||binding.repass.text.toString().isEmpty())
                Toast.makeText(this, "Please fill all the fields",Toast.LENGTH_SHORT).show()
        }
        else if (binding.pass.text.toString() != binding.repass.text.toString()){
            Toast.makeText(this,"password did not match,try again", Toast.LENGTH_SHORT).show()
        }
        else if (binding.pass.text.toString().length < 6){
            Toast.makeText(this,"your password must be 6 characters", Toast.LENGTH_SHORT).show()

        }
        else{
            Firebase.auth.createUserWithEmailAndPassword(binding.email.text.toString(),binding.pass.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this,LogActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }
            Toast.makeText(this,"Sign up Succsesfull",Toast.LENGTH_SHORT).show()


        }
    }
}