package swu.lj.novelwork.ui.advScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import swu.lj.novelwork.DB
import swu.lj.novelwork.adviceCard
import swu.lj.novelwork.iconText
import swu.lj.novelwork.navigationBar
import swu.lj.novelwork.ui.homeScreen.Message
import swu.lj.novelwork.ui.homeScreen.personInfo
import swu.lj.novelwork.ui.sortScreen.sortScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun advScreen(navController: NavController) {
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text="LJNovel",
                    color = MaterialTheme.colorScheme.surfaceTint,
                    fontSize = 25.sp
                )
                },
                //菜单导航icon
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("firstPage")  }) {
                        Icon(
                            painter = painterResource(id = swu.lj.novelwork.R.drawable.fanhui),
                            contentDescription = "Localized description",
                            Modifier.size(40.dp)
                        )
                    }
                },
                //搜索icon
                actions = {
                    IconButton(onClick = { /* TODO:搜索*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Localized description",
                            Modifier.size(40.dp)
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState())
            ) {

                for (i in 0..20){
                        personInfo(msg = Message("小说title","小说body"))
                }

            }

        }
    )
}


@Preview(showBackground = true)
@Composable
fun advSreenView(){
    val navController = rememberNavController()
    advScreen(navController = navController)

}