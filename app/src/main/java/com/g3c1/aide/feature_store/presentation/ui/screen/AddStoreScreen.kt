package com.g3c1.aide.feature_store.presentation.ui.screen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
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
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LifecycleCoroutineScope
import com.g3c1.aide.feature_store.presentation.ui.components.*
import com.g3c1.aide.feature_store.presentation.utils.getPath
import com.g3c1.aide.feature_store.presentation.utils.toRequestBody
import com.g3c1.aide.feature_store.presentation.viewmodel.StoreViewModel
import com.g3c1.aide.remote.utils.ApiState
import com.g3c1.aide.ui.theme.PretendardText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun AddStoreScreen(
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    viewModel: StoreViewModel,
    context: Context,
    goBackToStoreListPage: () -> Unit
) {
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
    val isLoading = remember {
        mutableStateOf(false)
    }
    Box {
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
                    permissionManager(context = context)
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
                    lifecycleCoroutineScope.launch {
                        val name = storeName.value
                        val description = storeDesCription.value
                        isLoading.value = true
                        viewModel.getImageUrl(File(storeImage.value!!.getPath(context)!!).toRequestBody())
                        getImageUrlRequest(viewModel, this, context) {
                            viewModel.addStore(
                                name = name,
                                description = description,
                                imageUrl = viewModel.getImageUrlRes.value.data!!.imageUrl
                            )
                            addStoreRequest(viewModel, lifecycleCoroutineScope, context) {
                                isLoading.value = false
                                Toast.makeText(context, "가게 등록에 성공했습니다!", Toast.LENGTH_SHORT).show()
                                getMyStoresInfoRequest(viewModel, lifecycleCoroutineScope, context)
                                goBackToStoreListPage()
                            }
                            this.cancel()
                        }
                    }
                }
            }
        }
    }
    Progressbar(isLoading.value)
}

private fun getImageUrlRequest(
    viewModel: StoreViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
    onSuccess: () -> Unit
) {
    coroutineScope.launch {
        viewModel.getImageUrlRes.collect {
            when (it) {
                is ApiState.Success -> {
                    onSuccess()
                    viewModel.getImageUrlRes.value = ApiState.Loading()
                }
                is ApiState.Error -> {
                    Toast.makeText(context, "이미지 용량이 너무 큽니다.", Toast.LENGTH_SHORT).show()
                }
                is ApiState.Loading -> {
                }
            }
        }
    }
}

private fun addStoreRequest(
    viewModel: StoreViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
    onSuccess: () -> Unit
) {
    coroutineScope.launch {
        viewModel.addStoreRes.collect {
            when (it) {
                is ApiState.Success -> {
                    onSuccess()
                    viewModel.addStoreRes.value = ApiState.Loading()
                }
                is ApiState.Error -> {
                    Toast.makeText(context, "가게를 등록할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
                is ApiState.Loading -> {
                }
            }
        }
    }
}

private fun getMyStoresInfoRequest(
    viewModel: StoreViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
) {
    viewModel.getMyStoresInfoRequest()
    coroutineScope.launch {
        viewModel.getMyStoresRes.collect { result ->
            when (result) {
                is ApiState.Success -> {
                    viewModel.getMyStoresRes.value = ApiState.Loading()
                }
                is ApiState.Error -> {
                    Toast.makeText(
                        context, "알수 없는 오류가 발생했습니다.", Toast.LENGTH_SHORT
                    ).show()
                    viewModel.getMyStoresRes.value = ApiState.Loading()
                }
                is ApiState.Loading -> {
                }
            }
        }
    }
}

private fun permissionManager(context: Context) {
    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            1
        )
    }
}