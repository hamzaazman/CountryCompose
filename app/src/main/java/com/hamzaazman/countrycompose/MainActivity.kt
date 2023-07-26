package com.hamzaazman.countrycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hamzaazman.countrycompose.navigation.SetupNavGraph
import com.hamzaazman.countrycompose.ui.theme.CountryComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryComposeTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

const val DETAIL_NAME_ARG = "name"

sealed class Screen(val route: String) {
    object Country : Screen(route = "countryScreen")
    object Detail : Screen(route = "detailScreen/{$DETAIL_NAME_ARG}") {
        fun passCountryNameArg(name: String): String {
            return "detailScreen/$name"
        }
    }
}