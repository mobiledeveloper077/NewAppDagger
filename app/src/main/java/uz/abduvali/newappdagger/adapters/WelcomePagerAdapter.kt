package uz.abduvali.newappdagger.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abduvali.newappdagger.databinding.ItemWelcomeBinding

class WelcomePagerAdapter(private val list: List<Int>) :
    RecyclerView.Adapter<WelcomePagerAdapter.Vh>() {

    class Vh(val binding: ItemWelcomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemWelcomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.binding.apply {
            image.setImageResource(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}