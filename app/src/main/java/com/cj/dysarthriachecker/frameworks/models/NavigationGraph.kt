package com.cj.dysarthriachecker.frameworks.models

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cj.dysarthriachecker.check.ui.DysarthriaCheckView
import com.cj.dysarthriachecker.home.ui.HomeView
import com.cj.dysarthriachecker.hospitalMap.ui.HospitalMapView
import com.cj.dysarthriachecker.more.ui.MoreView
import com.cj.dysarthriachecker.train.ui.TrainView

@Composable
fun NavigationGraph(navController : NavHostController){
    NavHost(navController = navController, startDestination = BottomNavigationItem.Home.screenRoute){
        composable(BottomNavigationItem.Home.screenRoute){
            HomeView()
        }

        composable(BottomNavigationItem.HospitalMap.screenRoute){
            HospitalMapView()
        }

        composable(BottomNavigationItem.CheckDysarthria.screenRoute){
            DysarthriaCheckView()
        }

        composable(BottomNavigationItem.Train.screenRoute){
            TrainView()
        }

        composable(BottomNavigationItem.More.screenRoute){
            MoreView()
        }
    }
}