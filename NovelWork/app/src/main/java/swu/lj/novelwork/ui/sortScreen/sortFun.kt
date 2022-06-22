package swu.lj.novelwork.ui.sortScreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.R
import swu.lj.novelwork.testDB
import swu.lj.novelwork.ui.bookShellScreen.bookShellItem
import swu.lj.novelwork.ui.bookShellScreen.bookShellItemMessage
import swu.lj.novelwork.ui.homeScreen.Message
import swu.lj.novelwork.ui.homeScreen.homeScreen
import swu.lj.novelwork.ui.homeScreen.personInfo

data class sortShellItemMessage(val coverUrl:Int,val title: String, val introduction: String)

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
                            val myJSONArray: JSONArray = testDB.getBookShell()
                            if (myJSONArray.length()!=0){
                                for (i in 0 until myJSONArray.length()){
                                    var obj:JSONObject =myJSONArray.get(i) as JSONObject
                                    sortItem(sortShellItemMessage(obj.getInt("image"),obj.getString("bookTitle"),obj.getString("introduction")),"bookScreen",navController,obj)
                                }
                            }
                        }

                    }
                }

                }
        }
    )

}



@Composable
fun sortItem(msg:sortShellItemMessage,routeDest:String,navController: NavController,jsonData:JSONObject){
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(all = 8.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(androidx.compose.material3.MaterialTheme.colorScheme.onBackground)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp)) {
            Image(
                painter = painterResource(id= msg.coverUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, androidx.compose.material.MaterialTheme.colors.secondaryVariant, CircleShape)
                    .clickable { navController.navigate("$routeDest?book=$jsonData") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            
            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.background,
            )

            // We toggle the isExpanded variable when we click on this Column
            Column() {
                Text(
                    text = msg.title,
                    fontSize = 30.sp,
                    color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                    style = androidx.compose.material.MaterialTheme.typography.subtitle2,
                    modifier = Modifier.clickable { navController.navigate("$routeDest?book=$jsonData") }
                )

                Spacer(modifier = Modifier.height(4.dp))

                androidx.compose.material.Surface(
                    shape = androidx.compose.material.MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                        .clickable { isExpanded = !isExpanded }
                ) {
                    Text(
                        text = msg.introduction,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = androidx.compose.material.MaterialTheme.typography.body2
                    )
                }
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun sortSreenView(){
    val navController = rememberNavController()
    sortScreen(navController = navController)

}