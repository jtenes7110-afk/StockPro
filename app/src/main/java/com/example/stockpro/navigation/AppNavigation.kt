package com.example.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.stockpro.screens.CatalogoScreen
import com.example.stockpro.screens.EditarProductoScreen
import com.example.stockpro.screens.LoginScreen
import com.example.stockpro.screens.ReporteScreen
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

        // LOGIN
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

        // CATALOGO
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

                onEditarProducto = { id ->

                    navController.navigate(
                        "${NavigationRoutes.EDICION}/$id"
                    )
                },

                onVerReporte = {

                    navController.navigate(
                        NavigationRoutes.REPORTE
                    )
                }
            )
        }

        // EDICION
        composable(
            route = NavigationRoutes.EDICION_ARG,
            arguments = listOf(
                navArgument("productoId") {
                    type = NavType.IntType
                }
            )
        ) {

            val productoId =
                it.arguments?.getInt("productoId") ?: 0

            EditarProductoScreen(
                productoId = productoId,
                viewModel = stockViewModel,
                onGuardar = {

                    navController.popBackStack()
                }
            )
        }

        // REPORTE
        composable(
            route = NavigationRoutes.REPORTE
        ) {

            ReporteScreen(
                viewModel = stockViewModel,
                onVolver = {

                    navController.popBackStack()
                }
            )
        }
    }
}