package com.g3c1.aide.feature_account.presentation.utils

import java.util.regex.Pattern

object Utils {
    fun checkIdPattern(userId: String): Boolean =
        userId.length < 8

    fun checkPasswordPattern(password: String): Boolean {
        val regularExpression = "^.*(?=^.{8,20}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"
        val passwordPattern = Pattern.compile(regularExpression)
        return !passwordPattern.matcher(password).find()
    }

    fun checkPasswordIsSame(password: String, confirmationPassword: String): Boolean =
        password != confirmationPassword
}