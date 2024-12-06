package com.zyablik.pr_9_12

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zyablik.pr_9_12.ui.theme.CustomTheme
import com.zyablik.pr_9_12.ui.theme.PR_912Theme
import com.zyablik.pr_9_12.ui.theme.Purple40
import com.zyablik.pr_9_12.ui.theme.Purple80
import com.zyablik.pr_9_12.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTheme{
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PR9(
                        name = "Danila",
                        surname = "Zhukov",
                        groupp = "IKBO-12-22",
                        modifier = Modifier.padding(innerPadding)
                            .background(Color.Blue)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun PR9(name: String, surname: String, groupp: String ,modifier: Modifier = Modifier){
    val arr: List<String> = mutableListOf("peach","apple","tangerine","lemon","blueberry","strawberry")
    Column(
        modifier = Modifier
            .size(500.dp)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.Magenta)
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                
        ){
            Text(
                text = "I'm $name $surname\nI'm a BIRD",
                textAlign = TextAlign.Center
            )
            Text(
                text = groupp,
            )
        }
        Box(
            Modifier
                .background(Color.Cyan)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()

        ){
            LazyRow {
                items(arr){ item ->
                    Text(
                        text = item,
                        Modifier.padding(2.dp)
                    )
                }
            }

        }
        Card(
            Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()

        ) {
            Text(
                text = "This is a card!"
            )
        }
    }

}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GreetingPreview() {
    CustomTheme() {
        PR9("Danila","Zhukov","IKBO-12-22")
    }
}