package com.example.cinematest.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import com.example.cinematest.R
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.ui.theme.CinemaTestTheme
import com.example.cinematest.ui.theme.Typography
import org.koin.compose.viewmodel.koinViewModel

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Shape
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.cinematest.ui.theme.MyTextStyles
import kotlinx.coroutines.launch

class FilmFragment : Fragment() {

    val bundle = Bundle()

    companion object {
        fun newInstance() = FilmFragment()
    }


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
                val viewModel = koinViewModel<FilmFragmentViewModel>()
                val darkTheme = isSystemInDarkTheme()



                CinemaTestTheme(
                    darkTheme = darkTheme,
                    dynamicColor = false,

                    ) {
                    FilmScreen(viewModel, "Фильмы")
                }


            }
        }
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FilmScreen(viewModel: FilmFragmentViewModel, title: String) {

        val state by viewModel.state.collectAsState()

        val listState = rememberLazyListState()


        val scope = rememberCoroutineScope()

        val snackbarHostState = remember { SnackbarHostState() }




        Scaffold(

            containerColor = MaterialTheme.colorScheme.primary,

            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState){
                    ErrorSnackbar(
                        onDismiss = { viewModel.start() },
                        snackbarHostState = snackbarHostState
                    )
                }

            },


            topBar = {

                TopAppBar(

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title,
                                color = MaterialTheme.colorScheme.onTertiary,
                                style = MyTextStyles.myTextStyleHeader,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                )
            },
            content = { innerpadding ->


                when (state) {

                    is State.Completed -> FilmScreen(title, viewModel, listState, innerpadding)
                    is State.Wait -> LoadingIndicator()
                    is State.Error -> {
                       lifecycleScope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Ошибка подключения сети",
                                actionLabel = "ПОВТОРИТЬ",


                                duration = SnackbarDuration.Indefinite
                            )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    viewModel.start()
                                }

                                SnackbarResult.Dismissed -> {
                                    //viewModel.start()
                                }
                            }


                        }
                    }

                    is State.ColdStart -> {

                        LoadingIndicator()

                    }
                }

            },
            modifier = Modifier.fillMaxSize()
        )

    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun FilmScreen(
        title: String,
        viewModel: FilmFragmentViewModel,
        listState: LazyListState,
        innerPadding: PaddingValues
    ) {
        Box() {

        }
        val itemState by viewModel.genre.collectAsState()
        val itemStateFilm by viewModel.films.collectAsState()

        val selectedIndex = remember { mutableIntStateOf(-1) }
        LazyColumn(

            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.primary)

        ) {

            item {
                Box(
                    Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        style = MyTextStyles.myTextStyleHeaderSecond,
                        color = MaterialTheme.colorScheme.onPrimary,
                        text = "Жанры",

                        modifier = Modifier.padding(16.dp)

                    )
                }

            }

            itemsIndexed(
                itemState
            )


            { index, genre ->
                genreCard(selectedIndex, index, genre, viewModel)

            }




            item {
                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "Фильмы",
                    modifier = Modifier.padding(16.dp),
                    style = MyTextStyles.myTextStyleHeaderSecond

                )
            }

            item {
                LazyVerticalGrid(

                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(1000.dp)
                        .fillMaxSize(),

                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)


                ) {

                    items(
                        itemStateFilm
                    ) { index ->

                        FilmItem(index, viewModel)
                    }

                }
            }


        }


    }


    @Composable
    private fun genreCard(
        selectedIndex: MutableIntState,
        index: Int,
        genre: String?,
        viewModel: FilmFragmentViewModel
    ) {
        Card(
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .selectable(
                    selected = selectedIndex.value == index,
                    onClick = {

                        selectedIndex.value = if (selectedIndex.value != index)
                            index else -1

                        genre?.let {
                            if (selectedIndex.value == index) {
                                viewModel.getGenre(it)
                            } else {
                                viewModel.getGenre(null)
                            }
                        }
                        lifecycleScope.launch {
//                            viewModel.start()
                        }

                    }


                ),
            colors = if ((selectedIndex.value == index))
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
            else
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)


        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            )

            {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    genre?.let {
                        Text(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = it,
                            style = MyTextStyles.myTextStyleGenreItem
                        )
                    }
                }

            }

        }
    }


    @Composable
    fun FilmItem(
        item: ModelCinema.Film?,
        viewModel: FilmFragmentViewModel
    ) {

        Card(

            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                // .background(MaterialTheme.colorScheme.secondary)
                .size(width = 0.dp, height = 270.dp)

                .clickable {

                    onItemDetailClick(item)
                    lifecycleScope.launch {
                        viewModel.start()
                    }

                },


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
                    item?.localizedName?.let {
                        Text(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            text = it,
                            style = MyTextStyles.myTextStyleFilmItem
                        )
                    }
                }
            }
        }

    }


    @Composable
    fun LoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }

    @Composable
    fun ErrorSnackbar(

        onDismiss: () -> Unit,
        snackbarHostState: SnackbarHostState,

        ) {
        SnackbarHost(
            hostState = snackbarHostState,
            Modifier.fillMaxWidth(),
            snackbar = { data ->

                Snackbar(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                    contentColor = MaterialTheme.colorScheme.secondary,
                    action = {
                        data?.  let { _ ->
                            TextButton(onClick = {

                                data.performAction()
                                onDismiss
                            }) {
                                Text(
                                    text = "ПОВТОРИТЬ",
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                ) {

                    Text(
                        text = "Ошибка подключения сети",
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        )
    }

    private fun onItemDetailClick(item: ModelCinema.Film?) {
        item?.id.let {
            if (it != null) {
                bundle.putInt("Arg", it)
            }
        }

        findNavController().navigate(R.id.action_mainFragment_to_itemFragment, bundle)
    }


    @Preview(
        device = "id:pixel_7a", showSystemUi = true,

        )
    @Composable
    fun preview() {

    }
}