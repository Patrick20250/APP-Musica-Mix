package Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ViewModel.LoginViewModel
import com.patrick.musicamix.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoEntrarTelaDeshBoard.setOnClickListener {

            val email = binding.edEmail.text.toString()
            val password = binding.edSenha.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()


            } else {
                loginViewModel.login(email,password)

            }

        }
        binding.botaoVoltarTelaDeIntro.setOnClickListener{

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()

        }
        loginViewModel.loginStatus.observe (this){
            status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

       loginViewModel.navigateHome.observe(this){
           navigate ->
           if(navigate){
               val intent = Intent(this, DashboardActivity::class.java)
               startActivity(intent)
               finish()
               loginViewModel.resetNavigateHome()
           }
       }

    }
    fun resetNavigateHome(view: View) { // Declaração da função navigateToSignIn
        startActivity(Intent(this, SignUpActivity::class.java)) // Inicia a SignInActivity
    }
}
