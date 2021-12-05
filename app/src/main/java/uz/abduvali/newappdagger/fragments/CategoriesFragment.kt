package uz.abduvali.newappdagger.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.App
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.adapters.TopicAdapter
import uz.abduvali.newappdagger.database.dao.TopicDao
import uz.abduvali.newappdagger.database.entity.TopicEntity
import uz.abduvali.newappdagger.databinding.FragmentCategoriesBinding
import uz.abduvali.newappdagger.utils.setColor
import javax.inject.Inject

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    @Inject
    lateinit var topicDao: TopicDao
    private val binding by viewBinding(FragmentCategoriesBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rv.adapter =
                TopicAdapter(topicDao.getTopics(), object : TopicAdapter.OnItemClickListener {
                    override fun onClick(
                        topic: TopicEntity,
                        textView: TextView,
                        linearLayout: LinearLayout
                    ) {
                        textView.setColor(requireContext(), topic.isSelected)
                        if (topic.isSelected) {
                            topic.isSelected = false
                            topicDao.updateTopic(topic)
                            linearLayout.setBackgroundColor(Color.parseColor("#f3f4f6"))
                        } else {
                            linearLayout.setBackgroundColor(Color.parseColor("#475AD7"))
                            topic.isSelected = true
                            topicDao.updateTopic(topic)
                        }
                    }
                })
        }
    }
}