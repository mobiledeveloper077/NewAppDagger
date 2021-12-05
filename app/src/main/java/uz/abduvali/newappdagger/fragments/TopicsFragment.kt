package uz.abduvali.newappdagger.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.App
import uz.abduvali.newappdagger.MainActivity
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.adapters.TopicAdapter
import uz.abduvali.newappdagger.database.dao.TopicDao
import uz.abduvali.newappdagger.database.entity.TopicEntity
import uz.abduvali.newappdagger.databinding.FragmentTopicsBinding
import uz.abduvali.newappdagger.utils.setColor
import javax.inject.Inject

class TopicsFragment : Fragment(R.layout.fragment_topics) {

    @Inject
    lateinit var topicDao: TopicDao
    private val binding by viewBinding(FragmentTopicsBinding::bind)
    private lateinit var list: ArrayList<TopicEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        initList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rv.adapter = TopicAdapter(list, object : TopicAdapter.OnItemClickListener {
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
            button.setOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                activity?.finish()
            }
        }
    }

    private fun initList() {
        list = ArrayList()
        list.apply {
            addTopic(TopicEntity(0, title = "\uD83C\uDFAE   Gaming"))
            addTopic(TopicEntity(1, title = "⚖️   Politics"))
            addTopic(TopicEntity(2, title = "\uD83C\uDF1E   Life"))
            addTopic(TopicEntity(3, title = "\uD83C\uDFC8   Sports"))
            addTopic(TopicEntity(4, title = "\uD83D\uDC3B   Animals"))
            addTopic(TopicEntity(5, title = "\uD83C\uDF34   Nature"))
            addTopic(TopicEntity(6, title = "\uD83D\uDCDC   History"))
            addTopic(TopicEntity(7, title = "\uD83D\uDC57   Fashion"))
            addTopic(TopicEntity(8, title = "\uD83D\uDE37   Covid-19"))
            addTopic(TopicEntity(9, title = "⚔️   Middle East"))
        }
    }

    private fun addTopic(topicEntity: TopicEntity) {
        topicDao.addTopic(topicEntity)
        list.add(topicEntity)
    }
}