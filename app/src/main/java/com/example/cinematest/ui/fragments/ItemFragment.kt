package com.example.cinematest.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.withInfiniteAnimationFrameNanos
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.cinematest.R
import com.example.cinematest.entity.ModelCinema
import com.example.cinematest.ui.theme.CinemaTestTheme
import com.example.cinematest.ui.theme.MyTextStyles
import com.example.cinematest.ui.theme.Typography
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.roundToInt
import kotlin.math.roundToLong

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (activity as? AppCompatActivity)?.window?.statusBarColor =
                context?.let { ContextCompat.getColor(it, R.color.navy) }!!
        }


        // Inflate the layout for this fragment
        return return ComposeView(requireContext()).apply {

            setContent {


                val viewModel: FilmViewModel = koinViewModel<FilmViewModel>()
                lifecycleScope.launch {
                    val id = arguments?.getInt("Arg")
                    if (id != null) {
                        viewModel.getFilmId(id)
                    }
                }


                CinemaTestTheme(
                    darkTheme = false,
                    dynamicColor = false,

                    ) {
                    filmScreen(viewModel)
                }


            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun filmScreen(viewModel: FilmViewModel) {


        val filmDetail = viewModel.filmId.collectAsState().value

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
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

                                style = Typography.titleLarge,
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


            ) { innerPadding ->
            filmInfo(
                viewModel(),
                innerPadding
            )
        }


    }


    @Composable
    fun filmInfo(
        viewModel: FilmViewModel,
        innerPadding: PaddingValues
    ) {
        val filmDetail = viewModel.filmId.collectAsState().value
        // val itemState by viewModel.films.collectAsState()


        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                // .height(300.dp)
                //.padding(start = 16.dp)
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
                /*  AsyncImage (model = ImageRequest.Builder(LocalContext.current)
                  .data("https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg")
                  .crossfade(true)
                  .build(),
                  //placeholder = painterResource(R.drawable.placeholder),
                  contentDescription = "stringResource",
                  contentScale = ContentScale.Crop,
                  modifier = Modifier.clip(CircleShape),
              )*/
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                filmDetail?.name?.let {
                    Text(
                        text = it,
                        style = MyTextStyles.myTextStyle1

                    )
                }

            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(

                    text = filmDetail?.genres?.joinToString(", ") + ", " + filmDetail?.year,

                    style = MyTextStyles.myTextStyle2

                )
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(

                    text = String.format("%.1f", filmDetail?.rating),
                    modifier = Modifier.align(Alignment.Bottom),
                    style = MyTextStyles.myTextStyle3

                )
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Text(

                    text = "Кинопоиск",
                    modifier = Modifier.align(Alignment.Bottom),
                    style = MyTextStyles.myTextStyle2


                )
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(

                    text = filmDetail?.description.toString(),

                    style = MyTextStyles.myTextStyle4

                )
            }
        }

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
                    //.fillMaxWidth(1.toFloat())

                    .width(132.dp)
                    .height(201.dp),
                // .clip(RoundedCornerShape(4.dp)),


                contentDescription = "Черный квадрат"
            )

            /*AsyncImage (model = ImageRequest.Builder(LocalContext.current)
                .data("https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg")
                .crossfade(true)
                .build(),
            //placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "stringResource",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape),
                )*/
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
                    // fontFamily = FontFamily(Font(R.font.Roboto)),
                    fontWeight = FontWeight(700),
                    letterSpacing = 0.1.sp,
                    color = Color(0xFF000000)
                )

            )
        }
    }


}


/*companion object {
*/
/**
 * Use this factory method to create a new instance of
 * this fragment using the provided parameters.
 *
 * @param param1 Parameter 1.
 * @param param2 Parameter 2.
 * @return A new instance of fragment ItemFragment.
 *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/


