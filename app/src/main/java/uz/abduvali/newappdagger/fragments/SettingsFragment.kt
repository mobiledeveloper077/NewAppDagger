package uz.abduvali.newappdagger.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yariksoffice.lingver.Lingver
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.databinding.FragmentSettingsBinding
import uz.abduvali.newappdagger.utils.MySharedPreference
import uz.abduvali.newappdagger.utils.ThemeHelper

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private lateinit var mySharedPreference: MySharedPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedPreference = MySharedPreference(requireContext())
        binding.apply {
            setDarkMode(mySharedPreference.getPreferences("isDark") == "1")
            darkMode.setOnCheckedChangeListener { buttonView, isChecked ->
                setDarkMode(isChecked)
            }
            language.text = mySharedPreference.getPreferences("lang") ?: "ru"
            language.setOnClickListener {
                if (mySharedPreference.getPreferences("lang") == "ru") {
                    mySharedPreference.setPreferences("lang", "en")
                    language.text = "en"
                } else {
                    mySharedPreference.setPreferences("lang", "ru")
                    language.text = "ru"
                }
                Lingver.getInstance()
                    .setLocale(requireContext(), mySharedPreference.getPreferences("lang") ?: "ru")
            }
        }
    }

    private fun setDarkMode(boolean: Boolean) {
        if (boolean) {
            ThemeHelper.applyTheme(ThemeHelper.darkMode)
            binding.darkMode.isChecked = true
            mySharedPreference.setPreferences("isDark", "1")
        } else {
            ThemeHelper.applyTheme(ThemeHelper.lightMode)
            mySharedPreference.setPreferences("isDark", "0")
            binding.darkMode.isChecked = false
        }
    }
}