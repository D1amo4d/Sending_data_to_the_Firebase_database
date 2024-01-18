package com.example.youtube_test

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var btnSend: MaterialButton
    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend = findViewById(R.id.btn_send)
        etName = findViewById(R.id.et_name)

        val database = FirebaseDatabase.getInstance()
        val references = database.getReference("users")

        btnSend.setOnClickListener {
            val userNameText = etName.text.toString()
            val userId = references.push().key
            val userReference = references.child(userId.toString())

            userReference.setValue(userNameText).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Сообщение отправлено")
                } else {
                    showToast("Ошибка при отправке сообщения")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
