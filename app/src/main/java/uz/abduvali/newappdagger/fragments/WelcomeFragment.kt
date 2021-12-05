package uz.abduvali.newappdagger.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.adapters.WelcomePagerAdapter
import uz.abduvali.newappdagger.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val binding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            pager.apply {
                adapter = WelcomePagerAdapter(initList())
                clipChildren = false
                clipToPadding = false
                setPadding(40, 0, 40, 0)
                overScrollMode = 2
                offscreenPageLimit = 3
            }
//            Compositepagetransformer
//            setOverScrollMode(2);
//            setPageTransformer(false, this);
//            setOffscreenPageLimit(3);
            tab.setViewPager2(pager)
            button.setOnClickListener {
                val position = pager.currentItem
                if (position != 3) {
                    pager.currentItem = position + 1
                } else {
                    findNavController().navigate(R.id.action_welcomeFragment_to_welcomePagerFragment)
                }
            }
        }
    }

    private fun initList(): List<Int> {
        val list = ArrayList<Int>()
        for (i in 1..2) {
            list.add(R.drawable.image_1)
            list.add(R.drawable.image_2)
        }
        return list
    }
}