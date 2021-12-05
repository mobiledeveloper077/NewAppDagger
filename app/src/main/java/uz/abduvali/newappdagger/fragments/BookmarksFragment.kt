package uz.abduvali.newappdagger.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.newappdagger.App
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.adapters.BookmarkAdapter
import uz.abduvali.newappdagger.database.dao.BookmarkDao
import uz.abduvali.newappdagger.database.entity.BookmarkEntity
import uz.abduvali.newappdagger.databinding.FragmentBookmarksBinding
import uz.abduvali.newappdagger.utils.hide
import uz.abduvali.newappdagger.utils.show
import uz.abduvali.newappdagger.utils.toNewEntity
import javax.inject.Inject

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    @Inject
    lateinit var bookmarkDao: BookmarkDao

    private val binding by viewBinding(FragmentBookmarksBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val bookmarkAdapter = BookmarkAdapter(object : BookmarkAdapter.OnItemClickListener {
                override fun onItemClick(bookmarkEntity: BookmarkEntity) {
                    findNavController().navigate(
                        R.id.newDataFragment,
                        Bundle().apply {
                            putSerializable("new", bookmarkEntity.toNewEntity())
                        }
                    )
                }
            })
            val list = bookmarkDao.getBookmarks()
            if (list.isNotEmpty()) {
                rv.show()
                noItem.hide()
                bookmarkAdapter.submitList(list)
                rv.adapter = bookmarkAdapter
            } else {
                rv.hide()
                noItem.show()
            }
        }
    }
}