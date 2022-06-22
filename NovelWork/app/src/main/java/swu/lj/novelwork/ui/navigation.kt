package swu.lj.novelwork.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.json.JSONObject
import swu.lj.novelwork.firstPage
import swu.lj.novelwork.ui.advScreen.advScreen
import swu.lj.novelwork.ui.bookScreen.bookScreen
import swu.lj.novelwork.ui.bookShellScreen.bookShellScreen
import swu.lj.novelwork.ui.homeScreen.Message
import swu.lj.novelwork.ui.homeScreen.homeScreen
import swu.lj.novelwork.ui.readBookScreen.readBookScreen
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
        //首页
        composable("firstPage") {
            firstPage(navController = navController)
        }
        //分类
        composable("sortScreen") {
            sortScreen(navController = navController)
        }
        //个人页
        composable("homeScreen") {
            homeScreen(navController = navController, Message("Android", "Composes我书书籍的的就飞机的诺斯基夫齐我觉得是的肌地方但是肤萨顿峰时代的萨顿峰时间地方简单发生的发萨顿峰"))
        }
        //榜单页
        composable("bookShellScreen?book={book}") {
            bookShellScreen(navController = navController)
        }
        //推荐页
        composable("advScreen") {
            advScreen(navController = navController)
        }
        //书籍详情页
        composable("bookScreen?book={book}") {backStackEntry ->
            bookScreen(navController = navController,JSONObject(backStackEntry.arguments?.getString("book").toString()))
        }
        //书籍详情页
        composable("readBookScreen") {
            readBookScreen(navController = navController)
        }
    }
}