package swu.lj.novelwork.ui.sortScreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import swu.lj.novelwork.R
import swu.lj.novelwork.ui.homeScreen.Message
import swu.lj.novelwork.ui.homeScreen.homeScreen
import swu.lj.novelwork.ui.homeScreen.personInfo

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun sortScreen(navController: NavController) {
    val mydatas = remember {
        mutableStateListOf("玄幻", "爱情", "科幻", "现实", "武侠")
    }
    val state = rememberPagerState(
        initialPage = 0,//初始页码
    )
    val scope = rememberCoroutineScope()
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
                    IconButton(onClick = { navController.navigate("firstPage") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.fanhui),
                            contentDescription = "返回按钮",
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
                Column {
                    ScrollableTabRow(selectedTabIndex = state.currentPage,//展示的页码，和Pager的保持一致
                        modifier = Modifier.background(color = Color.Green)) {
                        mydatas.forEachIndexed { index, data ->
                            Box(
                                Modifier
                                    .height(40.dp)
                                    .width(100.dp)
                                    .clickable {
                                        scope.launch {
                                            state.scrollToPage(
                                                index,
                                                0f
                                            )//Tab被点击后让Pager中内容动画形式滑动到目标页
                                        }
                                    }, contentAlignment = Alignment.Center) {
                                Text(text = data)
                            }
                        }
                    }
                    HorizontalPager(state = state,//Pager当前所在页数
                        count = mydatas.size, modifier = Modifier.background(MaterialTheme.colorScheme.background)) { pagePosition ->
                        Column() {
                            for (i in 0..20){
                                personInfo(msg = Message("小说title"+mydatas[pagePosition],"小说body"))
                            }
                        }

                    }
                }

                }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun sortSreenView(){
    val navController = rememberNavController()
    sortScreen(navController = navController)

}