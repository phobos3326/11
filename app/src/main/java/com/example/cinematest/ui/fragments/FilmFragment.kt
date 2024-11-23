package com.example.cinematest.ui.fragments

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.cinematest.R
import com.example.cinematest.ui.theme.CinemaTestTheme
import com.example.cinematest.ui.theme.MyTextStyles
import com.example.cinematest.ui.theme.Navy
import com.example.cinematest.ui.theme.Typography

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

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
              (activity as? AppCompatActivity)?.window?.statusBarColor =
                  context?.let { ContextCompat.getColor(it, R.color.navy) }!!
          }

        return ComposeView(requireContext()).apply {
            setContent {
                CinemaTestTheme(
                        darkTheme = false,
                    dynamicColor = true,

                    ){
                    SmallTopAppBarExample("Фильмы")
                }


                /*CinemaTestTheme(
                    darkTheme = false,
                    dynamicColor = false,

                    ) {


                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    ) { innerPadding ->



                        Column(

                            modifier = Modifier
                                .padding(innerPadding)


                        ) {
                            CustomActionBar(
                                title = stringResource(R.string.films),

                                onBackPress = { *//* Handle back press *//* }
                            )
                            // Your screen content goes here
                            Text(
                                color = MaterialTheme.colorScheme.primary,
                                text = "Helloo55555"
                            )

                    }
                    }*/


                }
            }
        }
    }



/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomActionBar(
    title: String,
    //onBackPress: () -> Unit
) {


    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()


            .height(56.dp)
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x24000000),
                ambientColor = Color(0x24000000)
            ),
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Navy),

        title = {


                Text(
                    text = title,
                    style = MyTextStyles.myTextStyle,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign. // Центрирование текста
                )


        },
        *//*navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },*//*


    )*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarExample( title: String) {
    Scaffold(

        topBar = {

            TopAppBar(
                /*modifier = Modifier
                    .height(56.dp),*/
                colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = MaterialTheme.colorScheme.primaryContainer,

                    colorResource(id = R.color.navy),
                    titleContentColor = MaterialTheme.colorScheme.primary,

                ),
                title = {
                    Box(modifier = Modifier
                        .fillMaxWidth()

                        .wrapContentHeight(),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = title,

                            style = Typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center)
                }

                }
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }

}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    val range = 1..100

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(range.count()) { index ->
            Text(
                style = MyTextStyles.myTextStyle,
                text = "- List item number ${index + 1}")
        }
    }
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

   // CustomActionBar(title = "название")
    /*Text(modifier = Modifier
        .size(50.dp)
        , text = "Helloo5555",
        textAlign = TextAlign.Center)*/

    SmallTopAppBarExample("Фильмы")
}