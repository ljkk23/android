package swu.lj.novelwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.json.JSONObject
import swu.lj.novelwork.ui.myNavHost
import swu.lj.novelwork.ui.theme.AppTheme
import swu.lj.novelwork.ui.tools.dataTools


val DB=dataTools()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            body()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun firstPage(navController: NavController) {

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text="LJNovel",
                    color = androidx.compose.material3.MaterialTheme.colorScheme.surfaceTint,
                    fontSize = 25.sp
                    )
                        },
                //菜单导航icon
                navigationIcon = {
                    IconButton(onClick = { /* TODO:抽屉弹出*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
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
        bottomBar = {
                    navigationBar(navController)
    },
        content = { innerPadding ->
            Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                        .verticalScroll(rememberScrollState())
                ) {
                    //Spacer(modifier = Modifier.size(10.dp))
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "概述",
                            Modifier
                                .padding(15.dp, 10.dp, 15.dp, 10.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )
                    }
                    //icon图片
                    Row() {
                        Image(

                            painter = painterResource(id = R.drawable.sort),
                            contentDescription = "分类",
                            Modifier
                                .padding(50.dp, 10.dp, 30.dp, 5.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                                .background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline)
                                .size(55.dp)
                                .clickable { navController.navigate("sortScreen") }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.tongji),
                            contentDescription = "我的书架",
                            Modifier
                                .padding(40.dp, 10.dp, 30.dp, 5.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                                .background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline)
                                .size(55.dp)
                                .clickable { navController.navigate("bookShellScreen") }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.remen),
                            contentDescription = "推荐",
                            Modifier
                                .padding(40.dp, 10.dp, 30.dp, 5.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                                .background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline)
                                .size(55.dp)
                                .clickable { navController.navigate("advScreen") }
                        )

                    }
                    //icon文字
                    Row() {
                        iconText(text = "分类")
                        iconText(text = "书架")
                        iconText(text = "推荐")
                    }
                    //推荐box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .size(400.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(androidx.compose.material3.MaterialTheme.colorScheme.onBackground)

                    ) {
                        val cardJsonArray = DB.getAdviceCard()
                        LazyColumn {
                            item {
                                Row() {
                                    for (i in 1..2) {
                                        adviceCard(jsonData = cardJsonArray.getJSONObject(i),navController)
                                    }
                                }
                                Row() {
                                    for (i in 3..4) {
                                        adviceCard(jsonData = cardJsonArray.getJSONObject(i),navController)
                                    }
                                }
                                Row() {
                                    for (i in 5..6) {
                                        adviceCard(jsonData = cardJsonArray.getJSONObject(i),navController)
                                    }
                                }
                            }
                        }


                    }

                }

        }
    )
}
//icon的text
@Composable
fun iconText(text:String) {
    Text(
        modifier = Modifier
        .padding(59.dp, 0.dp, 30.dp, 10.dp),
        text = text,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
        )

}
//推荐card设置
@Composable
fun adviceCard(jsonData:JSONObject,navController:NavController) {
    Column(modifier = Modifier.clickable { navController.navigate("bookScreen") }) {
//
        Image(
            painter = painterResource(id = jsonData.getInt("image")),
            contentDescription = "概述",
            Modifier
                .padding(30.dp, 20.dp, 10.dp, 0.dp)
                .width(130.dp)
                .height(170.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop,
            )
        //title
        Text(
            text = jsonData.getString("title"),
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(30.dp, 5.dp)

        )
        //简单介绍
        Text(
            text = jsonData.getString("introduction"),
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .padding(30.dp, 0.dp, 10.dp, 0.dp)
                .width(135.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )
    }
}

@Composable
fun navigationBar(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("主页", "个人")
    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    ) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    if (index==1){
                        navController.navigate("homeScreen")
                    }else if (index==0){
                        navController.navigate("firstPage")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        firstPage(rememberNavController())
    }
}
@Composable
fun body() {
    AppTheme {
        val navController = rememberNavController()
        myNavHost(navController = navController)
    }
}