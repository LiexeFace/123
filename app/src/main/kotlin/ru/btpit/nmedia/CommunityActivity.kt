package ru.btpit.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class CommunityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        val subscribeButton: Button = findViewById(R.id.subscribeButton)
        val messageButton: Button = findViewById(R.id.messageButton)

        subscribeButton.setOnClickListener {
            // Логика подписки на сообщество
            Toast.makeText(this, "Вы подписались на сообщество!", Toast.LENGTH_SHORT).show()
        }

        messageButton.setOnClickListener {
            // Логика для отправки сообщения
            Toast.makeText(this, "Открыть чат для сообщения", Toast.LENGTH_SHORT).show()
        }
    }
}
