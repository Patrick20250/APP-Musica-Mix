package ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    private val _navigateHome = MutableLiveData<Boolean>()
    val navigateHome: LiveData<Boolean> get() = _navigateHome

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _loginStatus.value = "Email e senha não podem estar vazios."
            return

        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginStatus.value = "Login bem sucedido!"
                    _navigateHome.value = true
                } else {
                    _loginStatus.value = "Falha no login${task.exception?.message}"
                }

            }
        fun resetNavigateHome() {
            _navigateHome.value = false

        }


    }
}