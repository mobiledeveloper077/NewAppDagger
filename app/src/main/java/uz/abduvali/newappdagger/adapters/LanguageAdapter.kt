package uz.abduvali.newappdagger.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.abduvali.newappdagger.databinding.ItemTopicBinding
import uz.abduvali.newappdagger.models.Language

class LanguageAdapter(
    private val list: List<Language>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<LanguageAdapter.Vh>() {

    interface OnItemClickListener {
        fun onClick(language: Language, textView: TextView, linearLayout: LinearLayout)
    }

    class Vh(val binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.binding.apply {
            val item = list[position]
            topic.text = item.title
            if (topic.isSelected) {
                layout.setBackgroundColor(Color.parseColor("#475AD7"))
            }
            topic.setOnClickListener {
                onItemClickListener.onClick(item, topic, layout)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}