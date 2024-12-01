package com.example.cinematest.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.runtime.mutableStateOf
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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
                val viewModel: FilmViewModel = koinViewModel<FilmViewModel>()
                val darkTheme = isSystemInDarkTheme()



                CinemaTestTheme(
                    darkTheme = darkTheme,
                    dynamicColor = false,

                    ) {
                    SmallTopAppBarExample(viewModel, "Фильмы")
                }


            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SmallTopAppBarExample(viewModel: FilmViewModel, title: String) {

        val state by viewModel.state.collectAsState()

        val listState = rememberLazyListState()

      /*  when(state){
            is State.Wait ->LoadingIndicator()
            is State.Completed -> FilmScreen(title, viewModel, listState)
            is State.Error -> ErrorSnackbar(" НЕТ ИНТЕРНЕТА", )
            is State.ColdStart -> {}

        }*/

        val scope = rememberCoroutineScope()
        //FilmScreen(title, viewModel, listState)
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(state) {
            if (state is State.Error){
                val result =snackbarHostState.showSnackbar(
                        message = "Snackbar",
                actionLabel = "Action",
                // Defaults to SnackbarDuration.Short
                duration = SnackbarDuration.Indefinite
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                       viewModel.start()
                    }
                    SnackbarResult.Dismissed -> {
                        viewModel.start()
                    }
                }
            }
        }



        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)

            },



            topBar = {

                TopAppBar(

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
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

                                style = Typography.titleLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                )
            },
           content = { innerpadding ->


               when (state) {

                   is State.Completed -> FilmScreen (title, viewModel, listState, innerpadding)
                   is State.Wait -> LoadingIndicator()
                   is State.Error -> ErrorSnackbar("message") { viewModel.start() }
                   is State.ColdStart -> {}
               }

           },
            modifier = Modifier.fillMaxSize()
        )
        /*{ innerPadding ->
            val itemState by viewModel.genre.collectAsState()
            val itemStateFilm by viewModel.films.collectAsState()

            val selectedIndex = remember { mutableIntStateOf(-1) }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)

            ) {

                item {
                    Box(
                        Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.primary,
                            text = "First Section Header",

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
                        color = MaterialTheme.colorScheme.primary,
                        text = "Second Section Header",
                        modifier = Modifier.padding(16.dp)
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

                            FilmItem(index)
                        }

                    }
                }


            }


        }*/


    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun FilmScreen(
        title: String,
        viewModel: FilmViewModel,
        listState: LazyListState,
        innerPadding:PaddingValues
    ) {
       Box(){

       }
            val itemState by viewModel.genre.collectAsState()
            val itemStateFilm by viewModel.films.collectAsState()

            val selectedIndex = remember { mutableIntStateOf(-1) }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)

            ) {

                item {
                    Box(
                        Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.primary,
                            text = "First Section Header",

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
                        color = MaterialTheme.colorScheme.primary,
                        text = "Second Section Header",
                        modifier = Modifier.padding(16.dp)
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

                            FilmItem(index)
                        }

                    }
                }


            }



    }

/*    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun FilmScreenError(
        title: String,
        viewModel: FilmViewModel,
        listState: LazyListState
    ) {
        Scaffold(

            topBar = {

                TopAppBar(

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
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

                                style = Typography.titleLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                )
            },
        ) { innerPadding ->

            Snackbar(
                modifier = Modifier
                    .padding(innerPadding)
                 action = {
                     TextButton(onClick = viewModel.start()) {
                         Text("Попробовать снова")
                     }
                 }
            ) {
                Text(text = "message")
            }

        }
    }*/



    @Composable
    private fun genreCard(
        selectedIndex: MutableIntState,
        index: Int,
        genre: String?,
        viewModel: FilmViewModel
    ) {
        Card(
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
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = it,
                        )
                    }
                }

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
                // .background(MaterialTheme.colorScheme.secondary)
                .size(width = 0.dp, height = 270.dp)

                .clickable {
                    onItemDetailClick(item)
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



    @Composable
    fun LoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun ErrorSnackbar(message: String,
                      onDismiss: () -> Unit
    ) {
        Snackbar(
            action = {
                TextButton(onClick = onDismiss) {
                    Text("Попробовать снова")
                }
            }
        ) {
            Text(text = message)
        }
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