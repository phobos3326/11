package com.example.cinematest

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {


                    Text(text = "Helloo55555")
                }
            }
        }
    }
}


@Preview(device = "id:pixel_7a")
@Composable
fun preview() {
    // SearchBar()
    // BarkHomeContent()
    //ImageWithOverlay()
    //FilmListItem1()
    Text(modifier = Modifier
        .size(50.dp)
        , text = "Helloo5555")
}