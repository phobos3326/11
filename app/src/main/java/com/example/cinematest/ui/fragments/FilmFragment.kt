package com.example.cinematest.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinematest.R
import com.example.cinematest.ui.theme.CinemaTestTheme
import com.example.cinematest.ui.theme.MyTextStyles
import com.example.cinematest.ui.theme.Navy

class FilmFragment : Fragment() {

    companion object {
        fun newInstance() = FilmFragment()
    }

    private val viewModel: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
              (activity as? AppCompatActivity)?.window?.statusBarColor =
                  context?.let { ContextCompat.getColor(it, R.color.navy) }!!
          }*/

        return ComposeView(requireContext()).apply {
            setContent {
                CinemaTestTheme(
                    darkTheme = false,
                    dynamicColor = false,

                    ) {


                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)

                        ) {
                            CustomActionBar(
                                title = "My Title",

                                // onBackPress = { /* Handle back press */ }
                            )
                            // Your screen content goes here
                            Text(
                                color = MaterialTheme.colorScheme.primary,
                                text = "Helloo55555"
                            )
                        }
                    }


                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomActionBar(
    title: String,
    //onBackPress: () -> Unit
) {

    val appBarColor = CinemaTestTheme {

    }
    TopAppBar(
        modifier = Modifier
            .padding(0.dp)
            .height(56.dp)
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x24000000),
                ambientColor = Color(0x24000000)
            ),
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Navy),
        title = {
            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp),
                        contentAlignment = Alignment.Center,
            )


            {


                Text(
                    text = stringResource(R.string.films),
                    style = MyTextStyles.myTextStyle
                )
            }

        },
        /*navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },*/


    )
}




@Preview(
    device = "id:pixel_7a", showSystemUi = true,

    )
@Composable
fun preview() {
    // SearchBar()
    // BarkHomeContent()
    //ImageWithOverlay()
    //FilmListItem1()

    CustomActionBar(title = "название")
    /*Text(modifier = Modifier
        .size(50.dp)
        , text = "Helloo5555",
        textAlign = TextAlign.Center)*/
}