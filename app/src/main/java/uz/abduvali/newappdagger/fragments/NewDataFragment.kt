package uz.abduvali.newappdagger.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.MainActivity
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.database.entity.NewEntity
import uz.abduvali.newappdagger.databinding.FragmentNewDataBinding
import uz.abduvali.newappdagger.utils.setImage

class NewDataFragment : Fragment(R.layout.fragment_new_data) {

    private val binding by viewBinding(FragmentNewDataBinding::bind)
    private lateinit var newEntity: NewEntity
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        mainActivity.hideNavigation()
        arguments.let {
            newEntity = it?.getSerializable("new") as NewEntity
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            newsDataTv.text = newEntity.content
            titleTv.text = newEntity.title
            image.setImage(
                newEntity.urlToImage
                    ?: "https://upload.wikipedia/commons/8/89/HD_transparent_picture.png"
            )
            imageOnBg.setImage(
                newEntity.urlToImage
                    ?: "https://upload.wikipedia/commons/8/89/HD_transparent_picture.png"
            )
            Picasso.get().load(newEntity.urlToImage).into(image)
            Picasso.get().load(newEntity.urlToImage).into(imageOnBg)
            backImg.setOnClickListener {
                findNavController().popBackStack()
            }

            shareImg.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, newEntity.description)
                shareIntent.putExtra(Intent.EXTRA_TITLE, newEntity.title)
                startActivity(Intent.createChooser(shareIntent, "Share"))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.showNavigation()
    }
}