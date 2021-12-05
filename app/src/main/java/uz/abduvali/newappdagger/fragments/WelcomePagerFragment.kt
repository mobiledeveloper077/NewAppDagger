package uz.abduvali.newappdagger.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.databinding.FragmentWelcomePagerBinding

class WelcomePagerFragment : Fragment(R.layout.fragment_welcome_pager) {

    private val binding by viewBinding(FragmentWelcomePagerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_welcomePagerFragment_to_topicsFragment)
            }
        }
    }
}