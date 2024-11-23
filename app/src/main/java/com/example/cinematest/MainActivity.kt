package com.example.cinematest

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.cinematest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)



      /*      fun getStatusBarHeight(): Int {
                var statusBarHeight = 0
                val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
                if (resourceId > 0) {
                    statusBarHeight = resources.getDimensionPixelSize(resourceId)
                }
                return statusBarHeight

            }

            val statusBarHeight: Int = getStatusBarHeight()

            // Create a new view that will cover the status bar.
            val statusBarView = View(this)
            statusBarView.layoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight)
            statusBarView.setBackgroundColor(getResources().getColor(R.color.navy))

            // Add the status bar view to the window.
            val window = window
            window.addContentView(
                statusBarView,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight)
            )*/



        }


}

/*
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
}*/
