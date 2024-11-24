package com.example.cinematest.ui.fragments

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.rememberImagePainter
import com.example.cinematest.R
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.ui.theme.CinemaTestTheme
import com.example.cinematest.ui.theme.MyTextStyles
import com.example.cinematest.ui.theme.Navy
import com.example.cinematest.ui.theme.Typography
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.viewmodel.koinViewModel

class FilmFragment : Fragment() {

    companion object {
        fun newInstance() = FilmFragment()
    }

    //private val viewModel: FilmViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        /*  viewLifecycleOwner.lifecycleScope.launch(start = CoroutineStart.DEFAULT) {
              viewModel.getFilm()
          }*/



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (activity as? AppCompatActivity)?.window?.statusBarColor =
                context?.let { ContextCompat.getColor(it, R.color.navy) }!!
        }

        return ComposeView(requireContext()).apply {

            setContent {
                val viewModel: FilmViewModel = koinViewModel<FilmViewModel>()
                CinemaTestTheme(
                    darkTheme = false,
                    dynamicColor = true,

                    ) {
                    SmallTopAppBarExample("Фильмы")
                }


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarExample(title: String) {
    Scaffold(

        topBar = {

            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(

                    colorResource(id = R.color.navy),
                    titleContentColor = MaterialTheme.colorScheme.primary,

                    ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()

                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,

                            style = Typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                }
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding, viewModel())
    }

}

@Composable
fun ScrollContent(
    innerPadding: PaddingValues,
    viewModel: FilmViewModel
) {
    val range = 1..100
    val itemState by viewModel.films.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        items(itemState) { index ->
            FilmItem(index)
        }

    }
}


@Composable
fun FilmItem(
    item: ModelCinema.Film?
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .size(width = 0.dp, height = 270.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Image(
                    painter = rememberImagePainter(item?.imageUrl),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(1.toFloat())
                        .size(0.dp, 222.dp)
                        .clip(RoundedCornerShape(4.dp)),


                    contentDescription = "Черный квадрат"
                )
            }
            Row {
                item?.name?.let {
                    Text(
                        modifier = Modifier,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = it,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            // fontFamily = FontFamily(Font(R.font.roboto_thin)),
                            fontWeight = FontWeight(700),
                            letterSpacing = 0.1.sp,
                            color = Color(0xFF000000)
                        )
                    )
                }
            }
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
    //FilmItem()
    //SmallTopAppBarExample("Фильмы")
}