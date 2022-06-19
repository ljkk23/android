package swu.lj.novelwork.ui.readBookScreen

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun readBookScreen(navController: NavController) {
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(

        scaffoldState = scaffoldState,
        drawerContent = {
            // Drawer content
            drawerView(navController = navController)

        },
        //顶部导航栏
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text="第XXX章",
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
                    text ="  故事的开始故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的故事的开始是这个同样的",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 16.dp, 0.dp, 0.dp)
                )
//                ModalDrawer(
//                    drawerState = drawerState,
//                    drawerContent = {
//                        Button(
//                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp),
//                            onClick = { scope.launch { drawerState.close() } },
//                            content = { Text("Close Drawer") }
//                        )
//                    },
//                    content = {
//                        Column(
//                            modifier = Modifier.fillMaxSize().padding(16.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
//                            Spacer(Modifier.height(20.dp))
//                            Button(onClick = { scope.launch { drawerState.open() } }) {
//                                Text("Click to open")
//                            }
//                        }
//                    }
//                )
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
fun drawerView(navController: NavController){
    Column() {
        Text(
            modifier = Modifier.clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第三章：大杀四方",
            fontSize = 30.sp,
            color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
            style = androidx.compose.material.MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier
                .clickable { navController.navigate("firstPage") }
                .padding(10.dp, 10.dp),
            text ="第三章：大杀四方",
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
    readBookScreen(navController = navController)

}