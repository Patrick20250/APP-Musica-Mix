package Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ViewModel.SignInViewModel
import android.widget.EditText
import com.patrick.musicamix.R
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.addTextChangedListener
import com.patrick.musicamix.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signInViewModel: SignInViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarListines()
        observaVielModel()
        onClick()
    }

    // Com figurando o nome,email e senha e colocando um icone de na cor verde para cada validação
    private fun configurarListines() {
        binding.btninscreveseTelaSingUp.setOnClickListener {

            val nome = binding.editHintNome.text.toString()
            val email = binding.editHintEmail.text.toString()
            val password = binding.editHintSenha.text.toString()



            when {

                nome.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }


                else -> {
                    signInViewModel.register(nome, email, password)

                }

            }


        }
        //esse bainding e referent ao iconi ficar verde quando ele for maior que 35 caracteres
        binding.editHintNome.addTextChangedListener { text ->

            ValidaCampo(text.toString().trim(), 3, binding.editHintNome)

        }

        //esse bainding e referent ao iconi ficar verde quando ele for maior que 5 caracteres
        binding.editHintEmail.addTextChangedListener { text ->
            ValidaCampo(text.toString().trim(), 5, binding.editHintEmail)

        }


        // esse bainding e referent ao iconi ficar verde quando ele for maior que 8 caracters
        binding.editHintSenha.addTextChangedListener { text ->
            ValidaCampo(text.toString().trim(), 8, binding.editHintSenha)
        }

    }

    // função que vai fazer a interação do botões
    private fun onClick() {
        binding.BtnVoltartelaIntro.setOnClickListener {

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)


        }

        binding.irParaTelaDeLogin.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
    // o erro está nessa função

    private fun observaVielModel() {

       signInViewModel.signUpStatus.observe (this){
           status ->
           Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
       }

        signInViewModel.navigateHome.observe (this){
            navegar ->
            if(navegar){
                startActivity(Intent(this, LoginActivity::class.java))
                signInViewModel.resetNavigateHome()

            }

        }

    }

    private fun ValidaCampo(texto: String, tamanhoMinimo: Int, campo: EditText) {
        if (texto.length >= tamanhoMinimo) {
            val drawable = ContextCompat.getDrawable(this, R.drawable.ico_check24)
            campo.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)

        } else {
            campo.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        }
    }


}













