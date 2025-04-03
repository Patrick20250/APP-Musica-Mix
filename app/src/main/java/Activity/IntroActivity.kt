package Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.patrick.musicamix.databinding.ActivityIntroBinding


class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding=ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            // quando o botão ele e clicado ele abre outra atividade ou seja outra tela de entrar
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        // quando o botão ele e clicado ele abre outra atividade ou seja outra tela de criar usuario
        binding.btnInscrevase.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }





    }

}