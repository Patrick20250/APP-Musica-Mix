package Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.patrick.musicamix.databinding.ActivityDashboardBinding
import com.patrick.musicamix.databinding.ActivityIntroBinding



class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(binding.root)

        binding.botaoDeshVoltarTelaIntro.setOnClickListener {

            val intent=(Intent(this, IntroActivity::class.java))
            startActivity(intent)
            finish()

        }




    }


}