package swu.lj.novelwork.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import swu.lj.novelwork.firstPage
import swu.lj.novelwork.ui.sortScreen.sortScreen

class navigation {
}



@Composable
fun myNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "firstPage"
    ) {
        composable("firstPage") {
            firstPage(navController = navController)
        }
        composable("sortScreen") {
            sortScreen(navController = navController)
        }
    }
}