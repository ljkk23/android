package swu.lj.novelwork

import android.graphics.Color.blue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONObject
import swu.lj.novelwork.ui.theme.AppTheme
import swu.lj.novelwork.ui.tools.dataTools

val DB=dataTools()
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Greeting()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    var name: String = "jason"
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
                            .clickable { /*TODO:跳转到分类*/ }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.tongji),
                        contentDescription = "榜单",
                        Modifier
                            .padding(40.dp, 10.dp, 30.dp, 5.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline)
                            .size(55.dp)
                            .clickable { /*TODO:跳转到榜单*/ }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.remen),
                        contentDescription = "分类",
                        Modifier
                            .padding(40.dp, 10.dp, 30.dp, 5.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline)
                            .size(55.dp)
                            .clickable { /*TODO:跳转到推荐*/ }
                    )

                }
                //icon文字
                Row (){
                    iconText(text = "分类")
                    iconText(text = "榜单")
                    iconText(text = "推荐")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .size(300.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.onBackground)

                ) {
                    val CardJsonArray=DB.getAdviceCard()
                    for (i in 1 until CardJsonArray.length()){
                        adviceCard(jsonData = CardJsonArray.getJSONObject(i))
                    }

                }
                repeat(100) {
                    Text(name, modifier = Modifier.padding(2.dp))
                }
                Text(text = "ddd", Modifier.padding(10.dp));
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
fun adviceCard(jsonData:JSONObject) {
    Column() {
        Image(
            painter = painterResource(id = jsonData.getInt("image")),
            contentDescription = "概述",
            Modifier.width(100.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop,

        )



    }


}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Greeting()
    }
}