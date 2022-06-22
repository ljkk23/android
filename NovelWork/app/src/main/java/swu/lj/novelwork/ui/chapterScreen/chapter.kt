package swu.lj.novelwork.ui.chapterScreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.*
import swu.lj.novelwork.R
import kotlin.concurrent.thread


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun chapterScreen(navController: NavController,url:JSONObject) {

    var bookChapter by remember { mutableStateOf(JSONArray()) }
    thread {
        bookChapter = swu.lj.novelwork.ui.readBookScreen.netWorktools.getBookChapterAndReadUrl(url.getString("url"))
        Log.e(TAG, "gsdfsdf: $bookChapter",)
    }
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
            )
        },
        content = { innerPadding ->
           LazyColumn(modifier = Modifier.padding(innerPadding)
           ){item{
               drawerView(navController = navController,bookChapter)
           }
           }

        }


    )
}

@Composable
fun drawerView(navController: NavController,bookChapter: JSONArray) {

    Column() {
        for (i in 0 until bookChapter.length()) {
            var obj: JSONObject = bookChapter.get(i) as JSONObject
            Text(
                modifier = Modifier
                    .clickable { navController.navigate("readBookScreen?url=$obj") }
                    .padding(10.dp, 10.dp),
                text = obj.getString("title"),
                fontSize = 30.sp,
                color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                style = androidx.compose.material.MaterialTheme.typography.subtitle2
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun homeScreenView(){
    val navController = rememberNavController()
    chapterScreen(navController = navController, url = JSONObject())
}