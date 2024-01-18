package com.example.youtube_test

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnSend: MaterialButton
    private lateinit var etName: EditText
    private lateinit var references: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend = findViewById(R.id.btn_send)
        etName = findViewById(R.id.et_name)
        references = FirebaseDatabase.getInstance().getReference("users")

        btnSend.setOnClickListener {
            sendMessageToDatabase(etName.text.toString())
        }

        val userIdToRetrieve = "здесь_ваш_идентификатор_пользователя"
        retrieveDataFromDatabase(userIdToRetrieve)
    }

    private fun sendMessageToDatabase(message: String) {
        val userId = references.push().key
        references.child(userId.toString()).setValue(message)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Сообщение отправлено")
                } else {
                    showToast("Ошибка при отправке сообщения")
                }
            }
    }

    private fun retrieveDataFromDatabase(userId: String) {
        references.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userName = dataSnapshot.getValue(String::class.java)
                userName?.let {
                    showToast("Имя пользователя: $it")
                    etName.setText(it)
                } ?: showToast("Пользователь не найден")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showToast("Ошибка при чтении данных из базы данных: ${databaseError.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

