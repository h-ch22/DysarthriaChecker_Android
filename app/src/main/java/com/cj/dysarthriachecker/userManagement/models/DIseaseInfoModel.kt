package com.cj.dysarthriachecker.userManagement.models

data class DiseaseInfoModel(
    var strokeDisabledLevel : Int? = null,
    var degenerativeBrainDiseaseLevel :  Int? = null,
    var peripheralNeuropathyLevel : Int? = null,
    var otherBrainDiseaseLevel : Int? = null,
    var functionalLangauageLevel : Int? = null,
    var larynxLevel : Int? = null,
    var oralLevel : Int? = null,
    var otherLanguageDiseaseLevel : Int? = null
)
