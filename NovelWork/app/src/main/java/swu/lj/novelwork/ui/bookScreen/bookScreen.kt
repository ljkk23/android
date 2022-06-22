package swu.lj.novelwork.ui.bookScreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import org.json.JSONObject
import swu.lj.novelwork.adviceCard
import swu.lj.novelwork.entity.BookShell
import swu.lj.novelwork.navigationBar
import swu.lj.novelwork.testDB
import swu.lj.novelwork.ui.homeScreen.Message
import swu.lj.novelwork.ui.homeScreen.personInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bookScreen(navController: NavController,msg:JSONObject) {
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
            )
        },
        bottomBar = {
            bookNavigationBar(navController,msg)
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
                        painter = painterResource(id = msg.getInt("image")),
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
                            text =msg.getString("bookTitle"),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(10.dp, 16.dp, 0.dp, 0.dp)
                        )
                        Text(
                            text =msg.getString("bookAuthor"),
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
                        //.size(400.dp)
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
                            text =msg.getString("introduction"),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                        )

                    }

                }
            }

        }
    )
}


@Composable
fun bookNavigationBar(navController: NavController,msg: JSONObject) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("立即阅读", "书架")
    //是否加入书架，true加入，false未加入
    val bookShell=BookShell(msg.getString("bookTitle"),msg.getInt("image"),msg.getString("introduction"),msg.getString("bookAuthor"),msg.getInt("readChapter"))
    var bookShellItem by remember { mutableStateOf(testDB.IsBookShell(bookShell.bookTitle)) }
    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                icon = { Icon(
                    painter = painterResource(id = if (index==0){swu.lj.novelwork.R.drawable.bofang}else{swu.lj.novelwork.R.drawable.shoucang}
                    ),
                    tint =
                    if (bookShellItem) {
                            Color.Black
                        } else {
                            Color.White
                        }
                    ,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                ) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    if (index==1){
                        if (!bookShellItem){
                            testDB.insertBookShell(bookShell)
                        }else{
                            testDB.deleteBookShell(bookShell)
                        }
                        bookShellItem=!bookShellItem
                    }else if (index==0){
                        navController.navigate("readBookScreen")
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun bookSreenView(){
    val navController = rememberNavController()
    bookScreen(navController = navController, msg = JSONObject())

}