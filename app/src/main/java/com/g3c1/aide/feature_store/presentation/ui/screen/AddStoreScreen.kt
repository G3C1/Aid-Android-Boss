package com.g3c1.aide.feature_store.presentation.ui.screen

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.g3c1.aide.feature_store.presentation.ui.components.AddStoreButton
import com.g3c1.aide.feature_store.presentation.ui.components.AddStorePageTopBar
import com.g3c1.aide.feature_store.presentation.ui.components.StoreImageField
import com.g3c1.aide.feature_store.presentation.ui.components.StoreInfoInputField
import com.g3c1.aide.feature_store.presentation.utils.getPath
import com.g3c1.aide.feature_store.presentation.utils.toRequestBody
import com.g3c1.aide.feature_store.presentation.viewmodel.StoreViewModel
import com.g3c1.aide.ui.theme.PretendardText
import java.io.File


@Composable
fun AddStoreScreen(viewModel: StoreViewModel, context: Context, goBackToStoreListPage: () -> Unit) {
    val storeImage = remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            storeImage.value = uri
        }
    val storeName = remember {
        mutableStateOf("")
    }
    val storeDesCription = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AddStorePageTopBar {
            goBackToStoreListPage()
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.07f))
            StoreImageField(storeImage.value) {
                launcher.launch(MediaStore.Images.Media.CONTENT_TYPE)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxWidth()
            ) {
                PretendardText(
                    text = "가게 이름을 정해주세요.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.01f))
                StoreInfoInputField(
                    text = storeName.value,
                    hint = "가게 이름",
                    isDescription = false,
                    onValueChange = {
                        storeName.value = it
                    }
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.07f))
                PretendardText(
                    text = "가게 설명을 적어주세요.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.01f))
                StoreInfoInputField(
                    text = storeDesCription.value,
                    hint = "가게 설명",
                    isDescription = true,
                    onValueChange = {
                        storeDesCription.value = it
                    })
            }
            AddStoreButton(isError = storeImage.value == null || storeName.value.isEmpty() || storeDesCription.value.isEmpty()) {
                Log.d("AddStore", storeImage.value!!.getPath(context)!!)
                viewModel.getImageUrl(File(storeImage.value!!.getPath(context)!!).toRequestBody())
            }
        }
    }
}