package com.example.cinematest

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.commit
import com.example.cinematest.databinding.ActivityMainBinding
import com.example.cinematest.ui.fragments.FilmFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)


            /*setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)*/


        }




}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
@Composable
fun MyApp() {
    val activity = LocalContext.current as? AppCompatActivity ?: return

    MaterialTheme {
        Text(text = "helloo")




            AndroidView(factory = { context ->
                FrameLayout(context).apply {
                    id = R.id.nav_host_fragment// Генерируем уникальный ID для контейнера
                }
            })

            // Проверяем, находится ли фрагмент уже в контейнере
            if (activity.supportFragmentManager.findFragmentById(R.id.mainFragment) == null) {
                activity.supportFragmentManager.commit {
                    replace(R.id.mainFragment, FilmFragment())
                }
            }


    }
}