package uz.abduvali.newappdagger.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.abduvali.newappdagger.fragments.CategoryPagerFragment

class CategoryPagerAdapter(val fragment: Fragment, val list: List<String>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryPagerFragment.newInstance(list[position])
    }
}