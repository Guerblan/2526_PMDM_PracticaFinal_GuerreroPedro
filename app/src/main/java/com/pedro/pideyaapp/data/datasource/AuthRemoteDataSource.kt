package com.pedro.pideyaapp.data.datasource

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRemoteDataSource(
    context: Context
) {

    private val appContext = context.applicationContext
    private val firebaseAuth: FirebaseAuth? = run {
        if (FirebaseApp.getApps(appContext).isEmpty()) {
            FirebaseApp.initializeApp(appContext)
        }
        if (FirebaseApp.getApps(appContext).isEmpty()) {
            null
        } else {
            FirebaseAuth.getInstance()
        }
    }

    fun isConfigured(): Boolean = firebaseAuth != null

    suspend fun login(email: String, password: String) {
        firebaseAuth?.signInWithEmailAndPassword(email, password)?.await()
    }

    suspend fun register(email: String, password: String) {
        firebaseAuth?.createUserWithEmailAndPassword(email, password)?.await()
        firebaseAuth?.signOut()
    }

    fun currentUserEmail(): String? = firebaseAuth?.currentUser?.email

    fun isLoggedIn(): Boolean = firebaseAuth?.currentUser != null

    fun logout() {
        firebaseAuth?.signOut()
    }
}
