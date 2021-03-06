package swu.lj.novelwork

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Nullable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.room.Room
import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.entity.BookShell
import swu.lj.novelwork.tools.NetWorktools
import swu.lj.novelwork.ui.myNavHost
import swu.lj.novelwork.ui.theme.AppTheme
import swu.lj.novelwork.tools.dataTools
import java.security.AccessController.getContext
import kotlin.concurrent.thread


val testDB=dataTools(getmainContext())
val netWorktools:NetWorktools = NetWorktools()

class MainActivity : ComponentActivity() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var mainContext: Context ;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainContext= applicationContext;
        netWorktools.getAdvBooks()
        setContent {
            body()
        }
    }
}
fun getmainContext():Context{
    return  MainActivity.mainContext
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun firstPage(navController: NavController) {
    var getFinished by remember { mutableStateOf(0) }
    var cardJsonArray by remember { mutableStateOf(JSONArray()) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text="LJNovel",
                    color = androidx.compose.material3.MaterialTheme.colorScheme.surfaceTint,
                    fontSize = 25.sp
                    )
                        },
                //????????????icon
                navigationIcon = {
                    IconButton(onClick = { /* TODO:????????????*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",
                            Modifier.size(40.dp)
                        )
                    }
                },
                //??????icon
                actions = {
                    IconButton(onClick = { /* TODO:??????*/ }) {
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
    }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            var jsonArray = testDB.getBookShell()
            Log.e(TAG, "firstPage: $jsonArray")
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "??????",
                    Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
            }
            //icon??????
            Row() {
                Image(

                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = "??????",
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
                    contentDescription = "????????????",
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
                    contentDescription = "??????",
                    Modifier
                        .padding(40.dp, 10.dp, 30.dp, 5.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                        .background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline)
                        .size(55.dp)
                        .clickable { navController.navigate("advScreen") }
                )

            }
            //icon??????
            Row() {
                iconText(text = "??????")
                iconText(text = "??????")
                iconText(text = "??????")
            }
            //??????box
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .size(400.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(androidx.compose.material3.MaterialTheme.colorScheme.onBackground)

            ) {

                thread {
                    cardJsonArray = netWorktools.getAdvBooks()
                    getFinished = 1
                    Log.e(TAG, "getAdvBooks: wanc",)
                }
                LazyColumn {
                    item {
                        if (cardJsonArray.length()!=0) {
                            for (i in 0 until (cardJsonArray.length()/2)) {
                                var conutI:Int=2*i
                                Row() {
                                    adviceCard(
                                        jsonData = cardJsonArray.getJSONObject(conutI),
                                        navController
                                    )
                                    adviceCard(
                                        jsonData = cardJsonArray.getJSONObject(conutI+1),
                                        navController
                                    )
                                }
                            }
                        }
                    }
                }


            }

        }

    }
}
//icon???text
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
//??????card??????
@Composable
fun adviceCard(jsonData:JSONObject,navController:NavController) {

    Column(modifier = Modifier.clickable { navController.navigate("bookScreen?book=$jsonData") }) {
//
        Image(
            painter = painterResource(id = jsonData.getInt("image")),
            contentDescription = "??????",
            Modifier
                .padding(30.dp, 20.dp, 10.dp, 0.dp)
                .width(130.dp)
                .height(170.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop,
            )
        //title
        Text(
            text = jsonData.getString("bookTitle"),
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(30.dp, 5.dp)

        )
        //????????????
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
    val items = listOf("??????", "??????")
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