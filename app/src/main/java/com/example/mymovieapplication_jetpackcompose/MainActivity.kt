package com.example.mymovieapplication_jetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mymovieapplication_jetpackcompose.navigation.MainScreenDestination
import com.example.mymovieapplication_jetpackcompose.navigation.Navigator
import com.example.mymovieapplication_jetpackcompose.navigation.NavigatorEvent
import com.example.mymovieapplication_jetpackcompose.ui.component.ActivityDrawer
import com.example.mymovieapplication_jetpackcompose.ui.component.DetailScreen
import com.example.mymovieapplication_jetpackcompose.ui.component.MainScreen
import com.example.mymovieapplication_jetpackcompose.ui.theme.MyMovieApplicationJetPackComposeTheme
import com.example.mymovieapplication_jetpackcompose.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MovieViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieListAPI()
        observeEvents()
        setContent {
            MyMovieApplicationJetPackComposeTheme(viewModel.darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    // Create a coroutineScope for the animation
                    val coroutineScope = rememberCoroutineScope()


                    Scaffold(scaffoldState = scaffoldState,
                        drawerContent = {
                            ActivityDrawer(
                                viewModel.darkTheme,
                                onThemeChanged = {
                                    viewModel.saveDarkThemeStatus(it)
                                    viewModel.darkTheme = it
                                })
                        },
                        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                        drawerShape = MaterialTheme.shapes.small,
                        drawerElevation = 2.dp,
                        drawerBackgroundColor = colorResource(id = R.color.white),
                        drawerContentColor = colorResource(id = R.color.black),
                        drawerScrimColor = colorResource(id = androidx.appcompat.R.color.material_blue_grey_800),
                        topBar = {
                            TopAppBar(backgroundColor = MaterialTheme.colors.surface, title = {

                                IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(2.dp, 4.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_action_name),
                                            contentDescription = null,
                                            tint = Color.Black,

                                            )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = stringResource(R.string.app_name))
                                    }

                                }
                            })

                        },
                        content = {
                            //AuroraScaffold(navigator)
                            val navController = rememberNavController()
                            NavHost(
                                navController = navController,
                                startDestination = "MovieListingScreen"
                            ) {
                                composable("MovieListingScreen") {
                                    MainScreen(
                                        viewModel.movieList, viewModel, navigation = navController
                                    )
                                }
                                composable("MovieDetailScreen") {
                                    DetailScreen(
                                        viewModel.movieDetailsObserver.value,
                                        navController,
                                        viewModel = viewModel
                                    )
                                }

                            }//addAuthDestination
                            //screen destination
                        })

                }
            }
        }
    }

    /*@Composable
    fun AuroraScaffold(navigator: Navigator) {
        val navController = rememberNavController()
        LaunchedEffect(navigator){
            navigator.destinations.collect{
                when (val event = it){
                    is NavigatorEvent.NavigateUp -> navController.navigateUp()
                    is NavigatorEvent.Directions -> navController.navigate(event.destination,event.builder)
                }
            }
        }
    }
*/

    private fun observeEvents() {
        viewModel.movieListObserver.observe(this) {
            viewModel.movieList.clear()
            viewModel.movieList.addAll(it)

        }
    }

}
