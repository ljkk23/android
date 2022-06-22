package swu.lj.novelwork.ui.bookShellScreen


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import swu.lj.novelwork.entity.BookShell
import swu.lj.novelwork.testDB
import swu.lj.novelwork.ui.homeScreen.Message
import swu.lj.novelwork.ui.homeScreen.personInfo
data class bookShellItemMessage(val coverUrl:Int,val title: String, val readChapter: Int?)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun bookShellScreen(navController: NavController) {
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
                val myJSONArray:JSONArray =testDB.getBookShell()
                if (myJSONArray.length()!=0){
                    for (i in 0 until myJSONArray.length()){
                        var obj:JSONObject =myJSONArray.get(i) as JSONObject
                        bookShellItem(bookShellItemMessage(obj.getInt("image"),obj.getString("bookTitle"),obj.getInt("readChapter")),"bookScreen",navController,obj)
                    }
                }

            }
        }
    )
}

@Composable
fun bookShellItem(msg:bookShellItemMessage,routeDest:String,navController: NavController,jsonData:JSONObject){
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(all = 8.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp)
                .clickable { navController.navigate("$routeDest?book=$jsonData") }) {
            Image(
                painter = painterResource(id = msg.coverUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(
                        1.5.dp,
                        androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))



            // We toggle the isExpanded variable when we click on this Column
            Column() {
                Text(
                    text = msg.title,
                    fontSize = 30.sp,
                    color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                    style = androidx.compose.material.MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                androidx.compose.material.Surface(
                    shape = androidx.compose.material.MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = MaterialTheme.colorScheme.background,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Column() {
                        Text(
                            text = "已读章节："+msg.readChapter.toString(),
                            fontSize = 15.sp,
                            modifier = Modifier.padding(all = 4.dp),
                            style = androidx.compose.material.MaterialTheme.typography.body2
                        )
                        Text(
                            text = "是否完结：否",
                            fontSize = 15.sp,
                            modifier = Modifier.padding(all = 4.dp),
                            style = androidx.compose.material.MaterialTheme.typography.body2
                        )
                    }

                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun bookShellScreenView(){
    val navController = rememberNavController()
    bookShellScreen(navController = navController)

}