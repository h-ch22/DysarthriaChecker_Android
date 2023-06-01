package com.cj.dysarthriachecker.userManagement.models

enum class SignUpFailType {
    WEAK_PASSWORD,
    PASSWORD_MISMATCH,
    EMPTY_FIELD,
    UNSUPPORTED_EMAIL_TYPE,
    ACCEPT_LICENSE,
    AUTH_ERROR
}