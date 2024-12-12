package com.zyablik.pr_9_12

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zyablik.pr_9_12.ui.theme.CustomTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                PR9(
                    name = "Danila",
                    surname = "Zhukov",
                    groupp = "IKBO-12-22",
                    navController
                )
            }
            composable("next") { NextScreen(navController)}
        }
    }
}

@Composable
fun NextScreen(navController: NavController) {
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding), ) {
            Box(Modifier.fillMaxWidth().background(Color.Red)) {
                Text(text = "Nothing to see here", color = Color.White, fontSize = 30.sp)
            }
            Box(Modifier.fillMaxWidth().background(Color.Green)) {
                Button(onClick = { navController.navigate("home") }) {
                    Text(text = "Back to home", textAlign = TextAlign.Center)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        Modifier.fillMaxHeight(0.05f)
    )
}

@Composable
fun PR9(name: String, surname: String, groupp: String, navController: NavController) {
    val arr: List<String> =
        mutableListOf("peach", "apple", "tangerine", "lemon", "blueberry", "strawberry")
    val someText = remember { mutableStateOf("This is a card!") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Text("Drawer title")
                    Button(
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isOpen) close() else open()
                                }
                            }
                            navController.navigate("next")
                        }
                    ) {
                        Text("Close me!")
                    }
                }
            }
        },
        gesturesEnabled = false
    ) {
        Scaffold(
            topBar = {
                AppBar(
                    title = "I'm a top bar :D"
                )
            },
            bottomBar = {
                AppBar(
                    title = "I'm a bottom bar >:D"
                )
            },
            content = { innerPadding ->
                Modifier.padding(innerPadding)
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxHeight(0.9f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(Color.Magenta)
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)

                    ) {
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

                    ) {
                        LazyRow {
                            items(arr) { item ->
                                Text(
                                    text = item,
                                    Modifier.padding(2.dp),
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }
                    Card(
                        Modifier
                            .background(Color.Green)
                            .fillMaxHeight(0.8f)
                            .fillMaxWidth()

                    ) {
                        Text(
                            text = someText.value,
                            fontSize = 40.sp
                        )
                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        someText.value = "You pushed FAB"
                        scope.launch {
                            drawerState.apply {
                                Log.d("rrr", isClosed.toString())
                                if (isClosed) open() else close()
                            }
                        }

                    }
                ) { Text("FAB") }
            }
        )
    }


}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun GreetingPreview() {
    CustomTheme() {
        Greeting()
    }
}