package swu.lj.novelwork.ui.bookScreen

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
import androidx.compose.material3.*
import androidx.compose.ui.layout.ContentScale
import swu.lj.novelwork.DB
import swu.lj.novelwork.adviceCard
import swu.lj.novelwork.ui.homeScreen.Message
import swu.lj.novelwork.ui.homeScreen.personInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bookScreen(navController: NavController) {
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
                Row {
                    Image(
                        painter = painterResource(id = swu.lj.novelwork.R.drawable.home2),
                        contentDescription = "概述",
                        Modifier
                            .width(170.dp)
                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop,
                    )

                    Column() {
                        Spacer(modifier = Modifier.size(40.dp))
                        Text(
                            text ="书名：《书的书名》 ",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(10.dp, 16.dp, 0.dp, 0.dp)
                        )
                        Text(
                            text ="作者：书的作者 ",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(39.dp, 19.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .size(400.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(15.dp))
                        //.background(androidx.compose.material3.MaterialTheme.colorScheme.onBackground)

                ) {
                    Column() {
                        Text(
                            text ="简介： ",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(10.dp, 16.dp, 0.dp, 0.dp)
                        )
                        Text(
                            text ="故事的开始是这个同样的",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(60.dp, 16.dp, 0.dp, 0.dp)
                        )

                    }

                }
            }

        }
    )
}


@Preview(showBackground = true)
@Composable
fun bookSreenView(){
    val navController = rememberNavController()
    bookScreen(navController = navController)

}