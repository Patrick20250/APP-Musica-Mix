package ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel : ViewModel() {

    private val _signUpStatus   = MutableLiveData<String>()

    val signUpStatus: LiveData<String> get() = _signUpStatus

    private val _navigateHome  = MutableLiveData<Boolean>()
    val navigateHome: LiveData<Boolean> get() = _navigateHome

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    fun register(nome: String, email: String, password: String) {

        if(nome.isBlank() || email.isBlank() || password.isBlank() ){
            _signUpStatus .value="Preencha todos os campos. Todos são obrigatórios."
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _signUpStatus .value = "Registro bem sucedido!"
                    _navigateHome.value = true

                } else {
                    _signUpStatus .value = "Falha no registro:${task.exception?.message}"
                }

            }
    }
    fun resetNavigateHome() {
        _navigateHome.value = false

    }




}