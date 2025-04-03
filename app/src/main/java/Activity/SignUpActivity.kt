package Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ViewModel.SignInViewModel
import com.patrick.musicamix.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val SignInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btninscreveseTelaSingUp.setOnClickListener {

            val nome = binding.editHintNome.text.toString()
            val email = binding.editHintEmail.text.toString()
            val password = binding.editHintSenha.text.toString()



            when {

                nome.isEmpty() || email.isEmpty() || password.isEmpty()-> {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }

                password.length < 8 -> {
                    Toast.makeText(
                        this,
                        "A senha deve conter no mínimo 8 caracteres",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                password !=password ->{
                    Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    SignInViewModel.register(nome, email, password)
                }

            }


        }
        binding.BtnVoltartelaIntro.setOnClickListener {

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)


        }
        SignInViewModel.signUpStatus.observe(this, { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })

        SignInViewModel.navigateHome.observe(this, { navigation ->
            if (navigation) {
                startActivity(Intent(this, LoginActivity::class.java))
                SignInViewModel.resetNavigateHome()

            }

        })

        binding.irParaTelaDeLogin.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()

        }


    }


}