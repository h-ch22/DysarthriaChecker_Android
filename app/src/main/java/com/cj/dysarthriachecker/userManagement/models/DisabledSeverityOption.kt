package com.cj.dysarthriachecker.userManagement.models

enum class DisabledSeverityOption {
    NONE{
        override fun getDescription() = "없음"
        override fun getCode() = 0
    }, WEAK{
        override fun getDescription() = "경도"
        override fun getCode() = 1
    }, MEDIUM{
        override fun getDescription() = "중등"
        override fun getCode() = 2
    }, HARD{
        override fun getDescription() = "고도"
        override fun getCode() = 3
    };

    abstract fun getDescription() : String
    abstract fun getCode() : Int
}