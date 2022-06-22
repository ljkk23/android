package swu.lj.novelwork.ui.readBookScreen

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DrawerValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.tools.NetWorktools
import kotlin.concurrent.thread


val netWorktools: NetWorktools = NetWorktools()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun readBookScreen(navController: NavController,url:JSONObject) {
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var body by remember { mutableStateOf("加载中") }
    thread {
        body=netWorktools.getBody(url.getString("url"))
        Log.e(ContentValues.TAG, "gsdfsdf: $body",)
    }
    Scaffold(
        scaffoldState = scaffoldState,
//        drawerContent = {
//            // Drawer content
//            //drawerView(navController = navController,bookChapter)
//            drawerView(navController = navController,bookChapter)
//        },
        //顶部导航栏
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text="第一章",
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
                //章节menu
                actions = {
                    //弹出章节的menu的icon
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",
                            Modifier.size(40.dp)
                        )


                    }
                }
            )
        },
        bottomBar = {
            readBookNavigationBar(navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = body,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 16.dp, 0.dp, 0.dp)
                )

            }
        }
    )
}


@Composable
fun readBookNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("上一章", "下一章")
    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                icon = { Icon(
                    painter = painterResource(id = if (index==0){swu.lj.novelwork.R.drawable.left}else{swu.lj.novelwork.R.drawable.right}

                    ),
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
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


@Composable
fun drawerView(navController: NavController,bookChapter:JSONArray){

    Column() {
//        for (i in 0 until bookChapter.length()){
//            var obj: JSONObject =bookChapter.get(i) as JSONObject
//            Text(
//                modifier = Modifier.clickable { navController.navigate("firstPage") }
//                    .padding(10.dp, 10.dp),
//                text =obj.getString("title"),
//                fontSize = 30.sp,
//                color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
//                style = androidx.compose.material.MaterialTheme.typography.subtitle2
//            )
//        }
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第一章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )



    }
}


@Preview(showBackground = true)
@Composable
fun readBookScreenView(){
    val navController = rememberNavController()
    readBookScreen(navController = navController,url = JSONObject())

}