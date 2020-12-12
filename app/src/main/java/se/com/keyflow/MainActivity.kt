package se.com.keyflow

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import se.mobileinteraction.keyflow.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val navController = findNavController(R.id.nav_host_fragment)

    }

}
