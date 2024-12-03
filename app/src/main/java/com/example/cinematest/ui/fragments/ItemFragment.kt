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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import com.example.cinematest.R
import com.example.cinematest.ui.theme.CinemaTestTheme
import com.example.cinematest.ui.theme.MyTextStyles
import com.example.cinematest.ui.theme.Typography
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ItemFragment : Fragment() {

    val bundle = Bundle()


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    @SuppressLint("CoroutineCreationDuringComposition")
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
                val viewModel = koinViewModel<ItemFragmentViewModel>()
                viewModel.start()
                lifecycleScope.launch(start = CoroutineStart.DEFAULT) {
                    val id = arguments?.getInt("Arg")
                    if (id != null) {
                        viewModel.getFilmId(id)
                    }
                }
                val darkTheme = isSystemInDarkTheme()
                CinemaTestTheme(
                    darkTheme = darkTheme,
                    dynamicColor = false,
                ) {
                    filmScreen(viewModel)
                }
            }
        }
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun filmScreen(viewModel: ItemFragmentViewModel) {

        val state by viewModel.state.collectAsState()
        val filmDetail = viewModel.filmId.collectAsState().value

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(state) {

            if (state is State.Error) {
                val result = snackbarHostState.showSnackbar(
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
            topBar = {

                TopAppBar(

                    modifier = Modifier
                        .fillMaxWidth(),


                    colors = TopAppBarColors(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onPrimary,
                        MaterialTheme.colorScheme.onPrimary,
                        MaterialTheme.colorScheme.onPrimary,
                        Color.Blue,

                        ),
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 48.dp)
                                .wrapContentHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = filmDetail?.name.toString(),

                                style = MyTextStyles.myTextStyleHeader,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                    },
                    navigationIcon = {
                        IconButton(onClick = {

                            findNavController().navigate(R.id.action_itemFragment_to_mainFragment)
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Local55ription"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },

            content = { innerpadding ->

                when (state) {
                    is State.Completed -> filmInfo(viewModel, innerpadding)
                    is State.Wait -> LoadingIndicator()
                    is State.Error -> {
                        lifecycleScope.launch {
                            val result = snackbarHostState.showSnackbar(
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

                                }
                            }
                        }
                    }

                    is State.ColdStart -> {
                        LoadingIndicator()
                    }
                }
            },

            )
    }


    @Composable
    fun filmInfo(
        viewModel: ItemFragmentViewModel,
        innerPadding: PaddingValues
    ) {
        val filmDetail = viewModel.filmId.collectAsState().value

        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
            ) {

                Image(
                    painter = rememberImagePainter(filmDetail?.imageUrl),
                    alignment = Alignment.Center,
                    modifier = Modifier

                        .fillMaxWidth(1.toFloat())
                        .size(132.dp, 201.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Fit,

                    contentDescription = "Черный квадрат"
                )
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                filmDetail?.name?.let {
                    Text(
                        text = it,
                        style = MyTextStyles.myTextStyleFilmName
                    )
                }
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(

                    text = filmDetail?.genres?.joinToString(", ") + ", " + filmDetail?.year,

                    style = MyTextStyles.myTextStyleFilmItem
                )
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(

                    text = String.format("%.1f", filmDetail?.rating),
                    modifier = Modifier.align(Alignment.Bottom),
                    style = MyTextStyles.myTextStyleRating

                )
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Text(

                    text = "Кинопоиск",
                    modifier = Modifier.align(Alignment.Bottom),
                    style = MyTextStyles.myTextStyleSource
                )
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(

                    text = filmDetail?.description.toString(),

                    style = MyTextStyles.myTextStyleDescription
                )
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

@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:width=1080px,height=2340px,dpi=440"
)
@Composable
fun preview1() {
    Column(
        // Modifier.padding(innerPadding)
        Modifier
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth()
        ) {

            Image(
                painter = rememberImagePainter("https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg"),
                // contentScale = ContentScale.Inside,
                alignment = Alignment.Center,
                modifier = Modifier

                    .width(132.dp)
                    .height(201.dp),


                contentDescription = "Черный квадрат"
            )


        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Между нами горы",
                style = TextStyle(
                    fontSize = 26.sp,
                    lineHeight = 32.sp,
                     fontFamily = FontFamily(Font(R.font.roboto_thin)),
                    fontWeight = FontWeight(700),
                    letterSpacing = 0.1.sp,
                    color = Color(0xFF000000)
                )

            )
        }
    }


}




