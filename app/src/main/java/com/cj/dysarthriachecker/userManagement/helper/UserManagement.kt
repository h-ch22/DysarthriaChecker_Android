package com.cj.dysarthriachecker.userManagement.helper

import com.cj.dysarthriachecker.frameworks.helper.AES256Util
import com.cj.dysarthriachecker.userManagement.models.UserInfoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserManagement {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    companion object{
        var userInfo : UserInfoModel? = null
    }

    fun signIn(email: String, password: String, completion: (Boolean) -> Unit){
        auth.signInWithEmailAndPassword(
            email, password
        ).addOnCompleteListener {
            if(it.isSuccessful){
                getUserInfo {
                    completion(it)
                    return@getUserInfo
                }
            } else{
                completion(false)
                return@addOnCompleteListener
            }
        }.addOnFailureListener {
            it.printStackTrace()
            completion(false)
            return@addOnFailureListener
        }
    }

    fun signUp(email: String, password: String, phone: String, name: String, completion: (Boolean) -> Unit){
        auth.createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener {
            if(it.isSuccessful){
                db.collection("Users").document(auth.currentUser?.uid ?: "")
                    .set(hashMapOf(
                        "name" to AES256Util.encrypt(name),
                        "phone" to AES256Util.encrypt(phone),
                        "email" to AES256Util.encrypt(phone)
                    )).addOnCompleteListener {
                        if(it.isSuccessful){
                            completion(true)
                            return@addOnCompleteListener
                        } else{
                            try{
                                auth.currentUser?.delete()

                            } catch(e: Exception){
                                e.printStackTrace()
                            } finally {
                                auth.signOut()
                                completion(false)
                                return@addOnCompleteListener
                            }
                        }
                    }
            } else{
                completion(false)
                return@addOnCompleteListener
            }
        }.addOnFailureListener {
            it.printStackTrace()
            completion(false)
            return@addOnFailureListener
        }
    }

    fun updateDiseaseData(
        strokeDisabledLevel : Int,
        degenerativeBrainDiseaseLevel : Int,
        peripheralNeuropathyLevel : Int,
        otherBrainDiseaseLevel : Int,
        functionalLanguageLevel : Int,
        larynxLevel : Int,
        oralLevel : Int,
        otherLanguageDiseaseLevel : Int,
        birthDay : String,
        completion : (Boolean) -> Unit
    ){
        val docRef = db.collection("Users").document(auth.currentUser?.uid ?: "")

        docRef.update("birthday", AES256Util.encrypt(birthDay)).addOnCompleteListener {
            if(it.isSuccessful){
                docRef.collection("DiseaseInfo").document("BasicDiseaseInfo")
                    .set(hashMapOf(
                        "stroke" to strokeDisabledLevel,
                        "degenerativeBrain" to degenerativeBrainDiseaseLevel,
                        "peripheralNeuropathy" to peripheralNeuropathyLevel,
                        "otherBrainDisease" to otherBrainDiseaseLevel,
                        "functionalLanguage" to functionalLanguageLevel,
                        "larynx" to larynxLevel,
                        "oral" to oralLevel,
                        "otherLanguageDisease" to otherLanguageDiseaseLevel
                    )).addOnCompleteListener {
                        if(it.isSuccessful){
                            getUserInfo {
                                completion(it)
                                return@getUserInfo
                            }
                        } else{
                            completion(false)
                            return@addOnCompleteListener
                        }
                    }.addOnFailureListener {
                        it.printStackTrace()
                        completion(false)
                        return@addOnFailureListener
                    }
            } else{
                completion(false)

            }
        }.addOnFailureListener {
            it.printStackTrace()
            completion(false)
        }
    }

    fun getUserInfo(completion: (Boolean) -> Unit){
        db.collection("Users").document(auth.currentUser?.uid ?: "")
            .get().addOnCompleteListener {
                if(it.isSuccessful){
                    val document = it.result

                    if(document != null && document.exists()){
                        val name = document.get("name") as? String ?: ""
                        val phone = document.get("phone") as? String ?: ""
                        val email = document.get("email") as? String ?: ""
                        val birthday = document.get("birthday") as? String ?: ""

                        userInfo = UserInfoModel(
                            name=name, phone=phone, email=email, birthDay=birthday, uid=auth.currentUser?.uid ?: ""
                        )

                        completion(true)
                        return@addOnCompleteListener
                    } else{
                        completion(false)
                        return@addOnCompleteListener
                    }
                } else{
                    completion(false)
                    return@addOnCompleteListener
                }
            }.addOnFailureListener {
                it.printStackTrace()
                completion(false)
                return@addOnFailureListener
            }
    }
}