package com.g3c1.aide.feature_store.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.presentation.ui.components.TopBar

@Composable
fun SelectStoreScreen(bossName: String, storeInfoList: List<StoreInfoDTO>) {
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "StoreListScreen") {
            composable("StoreListScreen") {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TopBar(bossName = bossName)
                    if (storeInfoList.isEmpty())
                        StoreListEmptyScreen {
                            navController.navigate("StoreAddPage") {
                                popUpTo("StoreAddPage") {
                                    inclusive = true
                                }
                            }
                        }
                    else
                        StoreListScreen(item = storeInfoList) {
                            navController.navigate("StoreAddPage") {
                                popUpTo("StoreAddPage") {
                                    inclusive = true
                                }
                            }
                        }
                }
            }
            composable("StoreAddPage") {
                AddStoreScreen {
                    navController.navigate("StoreListScreen") {
                        popUpTo("StoreListScreen") {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}