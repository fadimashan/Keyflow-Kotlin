package se.com.keyflow.ui.splash

import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import se.com.keyflow.util.ScreenUtils
import se.mobileinteraction.keyflow.R


class SplashFragment : Fragment(R.layout.fragment_splash) {

    val viewModel by viewModel<SplashViewModel>()

    override fun onResume() {
        super.onResume()
        img_logo.apply {
            translationY = ScreenUtils.dipsToPixel(120f)
            animate().alpha(1f).setStartDelay(500).setDuration(300).start()
            animate()
                .setInterpolator(DecelerateInterpolator())
                .translationY(0f)
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setStartDelay(800)
                .setDuration(800)

                .withEndAction {
                    navigateToDetails()
                }.start()
        }
    }

    private fun navigateToDetails() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToNavHostFragment().actionId,
            null,
            NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
        )
    }
}
