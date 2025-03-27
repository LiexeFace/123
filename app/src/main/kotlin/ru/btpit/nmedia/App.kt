package ru.btpit.nmedia

import android.app.Application
import ru.btpit.nmedia.repository.PostRepositoryInitializer

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Инициализируем репозиторий при запуске приложения
        PostRepositoryInitializer.initialize(this)
    }
} 