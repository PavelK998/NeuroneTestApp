package ru.pk.neuronetestapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.pk.neuronetestapp.presentation.screens.ProfileScreenRoot
import ru.pk.neuronetestapp.presentation.screens.ProfileViewModel

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.ProfileScreen
    ) {
        composable<Destination.ProfileScreen> {
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreenRoot(
                viewModel = viewModel,
                onPurchasesClick = {
                    navController.navigate(Destination.PurchasesScreen)
                },
                onRegisterBankClientClick = {
                    navController.navigate(Destination.RegisterScreen)
                }
            )
        }

        composable<Destination.RegisterScreen> {

        }

        composable<Destination.PurchasesScreen> {

        }
    }
}