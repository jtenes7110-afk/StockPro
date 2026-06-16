package com.example.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stockpro.screens.LoginScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.stockpro.screens.CatalogoScreen
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun AppNavigation(
    navController: NavHostController
) {

    val stockViewModel: StockViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {

        composable(
            route = NavigationRoutes.LOGIN
        ) {

            LoginScreen(
                onIngresar = { nombre ->

                    navController.navigate(
                        "${NavigationRoutes.CATALOGO}/$nombre"
                    )
                }
            )
        }

        composable(
            route = NavigationRoutes.CATALOGO_ARG,
            arguments = listOf(
                navArgument("nombreOperario") {
                    type = NavType.StringType
                }
            )
        ) {

            val nombre =
                it.arguments?.getString("nombreOperario")
                    ?: ""

            CatalogoScreen(
                nombreOperario = nombre,
                viewModel = stockViewModel,
                onEditarProducto = { },
                onVerReporte = { }
            )
        }
    }
}