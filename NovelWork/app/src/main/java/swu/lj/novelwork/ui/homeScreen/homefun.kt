package swu.lj.novelwork.ui.homeScreen

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
import swu.lj.novelwork.*
import swu.lj.novelwork.R

data class Message(val author: String, val body: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(navController: NavController,msg:Message) {

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
        bottomBar = {
            navigationBar(navController)
        },
        content = { innerPadding ->
           LazyColumn(modifier = Modifier.padding(innerPadding)
           ){
               item {
                   personInfo(msg = Message("刘剑","我是一个帅哥。而且还很的的浮点数 浮点数 的缩放撒旦发萨顿峰爱上发萨顿峰聪明"),"homeScreen",navController)
               }
           }



        }


    )
}
@Composable
fun personInfo(msg:Message,routeDest:String,navController: NavController){
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
                painter = painterResource(R.drawable.home),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
                    .clickable { navController.navigate(routeDest) }
            )
            Spacer(modifier = Modifier.width(8.dp))


            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if (isExpanded) androidx.compose.material3.MaterialTheme.colorScheme.background else androidx.compose.material3.MaterialTheme.colorScheme.background,
            )

            // We toggle the isExpanded variable when we click on this Column
            Column() {
                Text(
                    text = msg.author,
                    fontSize = 30.sp,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.clickable { navController.navigate(routeDest) }
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
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
                        text = msg.body,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun homeScreenView(){
    val navController = rememberNavController()
    homeScreen(navController = navController, msg = Message("Android", "Jetpack Composes我书书籍的的就飞机的诺斯基夫齐我觉得是的肌地方但是肤萨顿峰时代的萨顿峰时间地方简单发生的发萨顿峰"))
}