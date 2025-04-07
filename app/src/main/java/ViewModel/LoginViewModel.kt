package ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> = _loginStatus

    private val _navigateHome = MutableLiveData<Boolean>()
    val navigateHome: LiveData<Boolean> = _navigateHome


    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginStatus.value = "Email e senha não podem estar vazios."
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginStatus.value = "Login realizado com sucesso!"
                    _navigateHome.value = true
                } else {
                   val exception = task.exception
                    val errorManager = when (exception){
                        is FirebaseAuthInvalidUserException -> "Usuário  não cadastrado"
                        is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha invalidos."
                        is FirebaseAuthException -> "Erro de autenticação ${exception.message}"
                        else -> "Erro ao fazer Login Tente Novamente mais Tarde"

                    }
                    _loginStatus.value = errorManager

                }
            }
    }

    fun resetNavigateHome() {
        _navigateHome.value = false
    }
}