package uz.abduvali.newappdagger.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.databinding.FragmentLanguageBinding

class LanguageFragment : Fragment(R.layout.fragment_language) {

    private val binding by viewBinding(FragmentLanguageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}