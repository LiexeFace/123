package ru.btpit.nmedia.repository

import android.content.Context
import ru.btpit.nmedia.model.Post

object PostRepositoryInitializer {
    fun initialize(context: Context) {
        val repository = PostRepositoryJsonImpl(context)
        
        // Проверяем, есть ли уже посты в репозитории
        if (repository.getAll().isEmpty()) {
            // Создаем начальные посты
            val initialPosts = listOf(
                Post(
                    id = 1,
                    author = "БТПИТ",
                    content = "В 2025 году отмечается 300-летие Воронежской губернии. В честь этого важного события мы предлагаем окунуться в историю региона, узнать больше о наших славных земляках и познакомиться с самыми неожиданными фактами о родном крае.",
                    published = "19 марта в 13:36",
                    likes = 51999,
                    shares = 520,
                    views = 1300000,
                    comments = 1600,
                    imageUrl = "novost"
                ),
                Post(
                    id = 2,
                    author = "БТПИТ",
                    content = "В 2025 году отмечается 300-летие Воронежской губернии. В честь этого важного события мы предлагаем окунуться в историю региона, узнать больше о наших славных земляках и познакомиться с самыми неожиданными фактами о родном крае.",
                    published = "18 марта в 13:36",
                    likes = 48999,
                    shares = 480,
                    views = 1250000,
                    comments = 1450,
                    imageUrl = "novost2"
                ),
                Post(
                    id = 3,
                    author = "БТПИТ",
                    content = "В 2025 году отмечается 300-летие Воронежской губернии. В честь этого важного события мы предлагаем окунуться в историю региона, узнать больше о наших славных земляках и познакомиться с самыми неожиданными фактами о родном крае.",
                    published = "17 марта в 13:36",
                    likes = 45999,
                    shares = 450,
                    views = 1200000,
                    comments = 1300,
                    imageUrl = "novost3"
                )
            )
            
            // Сохраняем начальные посты
            initialPosts.forEach { post ->
                repository.save(post)
            }
        }
    }
} 