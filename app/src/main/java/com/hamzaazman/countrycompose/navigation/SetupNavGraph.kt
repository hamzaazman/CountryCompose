package com.hamzaazman.countrycompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hamzaazman.countrycompose.Screen
import com.hamzaazman.countrycompose.presentation.country.CountryScreen
import com.hamzaazman.countrycompose.presentation.detail.DetailScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Country.route
    ) {
        composable(route = Screen.Country.route) {
            CountryScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })
        ) {
            val args = it.arguments?.getString("name").toString()
            DetailScreen(navController = navController, nameArgs = args)
        }

    }

}