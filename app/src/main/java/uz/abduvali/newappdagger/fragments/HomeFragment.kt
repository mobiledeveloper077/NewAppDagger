package uz.abduvali.newappdagger.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abduvali.newappdagger.App
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.adapters.CategoryPagerAdapter
import uz.abduvali.newappdagger.adapters.NewDataAdapter
import uz.abduvali.newappdagger.database.entity.NewEntity
import uz.abduvali.newappdagger.databinding.FragmentHomeBinding
import uz.abduvali.newappdagger.databinding.ItemTabSelectedBinding
import uz.abduvali.newappdagger.databinding.ItemTabUnselectedBinding
import uz.abduvali.newappdagger.utils.MySharedPreference
import uz.abduvali.newappdagger.viewmodels.NewDataResource
import uz.abduvali.newappdagger.viewmodels.NewViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment(R.layout.fragment_home), CoroutineScope {

    @Inject
    lateinit var newViewModel: NewViewModel
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            pager.adapter = CategoryPagerAdapter(this@HomeFragment, list)
            pager.isUserInputEnabled = false
            TabLayoutMediator(
                tab, pager
            ) { tab, position ->
                val text = list[position]
                if (position == pager.currentItem) {
                    tab.customView = setSelectedTab(text)
                } else {
                    tab.customView = setUnselectedTab(text)
                }
            }.attach()
            tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    ItemTabSelectedBinding.bind(tab?.customView!!).apply {
                        card.setBackgroundColor(Color.parseColor("#475AD7"))
                        text.setTextColor(Color.WHITE)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    ItemTabUnselectedBinding.bind(tab?.customView!!).apply {
                        card.setBackgroundColor(Color.parseColor("#E8E8E8"))
                        text.setTextColor(Color.parseColor("#E8E8E8"))
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
//            search.setOnQueryTextFocusChangeListener { v, hasFocus ->
//
//            }
            val newDataAdapter = NewDataAdapter(object : NewDataAdapter.OnItemClickListener {
                override fun onItemClick(newEntity: NewEntity) {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_newDataFragment,
                        Bundle().apply {
                            putSerializable("new", newEntity)
                        }
                    )
                }
            })
            launch {
                newViewModel.getNewsData(
                    list.random(),
                    MySharedPreference(requireContext()).getPreferences("lang") ?: "en"
                ).collect {
                    when (it) {
                        is NewDataResource.Success -> {
                            newDataAdapter.submitList(it.list)
                            rv.adapter = newDataAdapter
                        }
                    }
                }
            }
        }
    }

    private fun setSelectedTab(text: String): View {
        val tabBinding = ItemTabSelectedBinding.inflate(layoutInflater)
        tabBinding.text.text = text
        return tabBinding.root
    }

    private fun setUnselectedTab(text: String): View {
        val tabBinding = ItemTabUnselectedBinding.inflate(layoutInflater)
        tabBinding.text.text = text
        return tabBinding.root
    }

    private val list = ArrayList<String>().apply {
        add("Sports")
        add("Politics")
        add("Life")
        add("Gaming")
        add("Animals")
        add("Nature")
        add("History")
        add("Fashion")
        add("Covid-19")
        add("Middle East")
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}