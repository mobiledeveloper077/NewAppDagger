package uz.abduvali.newappdagger.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abduvali.newappdagger.App
import uz.abduvali.newappdagger.R
import uz.abduvali.newappdagger.adapters.CategoryAdapter
import uz.abduvali.newappdagger.database.dao.BookmarkDao
import uz.abduvali.newappdagger.database.entity.NewEntity
import uz.abduvali.newappdagger.databinding.FragmentCategoryPagerBinding
import uz.abduvali.newappdagger.utils.MySharedPreference
import uz.abduvali.newappdagger.utils.hide
import uz.abduvali.newappdagger.utils.show
import uz.abduvali.newappdagger.utils.toBookmarkEntity
import uz.abduvali.newappdagger.viewmodels.NewDataResource
import uz.abduvali.newappdagger.viewmodels.NewViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "query"

class CategoryPagerFragment : Fragment(R.layout.fragment_category_pager), CoroutineScope {

    @Inject
    lateinit var newViewModel: NewViewModel

    @Inject
    lateinit var bookmarkDao: BookmarkDao

    private lateinit var categoryAdapter: CategoryAdapter
    private var query: String? = null
    private val binding by viewBinding(FragmentCategoryPagerBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        arguments?.let {
            query = it.getString(ARG_PARAM1)
        }
        val bookmarks = bookmarkDao.getBookmarks().toMutableList() as ArrayList
        categoryAdapter = CategoryAdapter(bookmarks, object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(newEntity: NewEntity) {
                findNavController().navigate(
                    R.id.action_homeFragment_to_newDataFragment,
                    Bundle().apply {
                        putSerializable("new", newEntity)
                    }
                )
            }

            override fun onBookmarkClick(newEntity: NewEntity, imageView: ImageView) {
                val bookmark = newEntity.toBookmarkEntity()
                if (bookmarks.any { it.url == newEntity.url }) {
                    bookmarkDao.deleteBookmark(bookmark)
                    bookmarks.remove(bookmark)
                    imageView.setImageResource(R.drawable.ic_bookmark)
                } else {
                    bookmarkDao.addBookmark(bookmark)
                    bookmarks.add(bookmark)
                    imageView.setImageResource(R.drawable.ic_bookmarked)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            refresh()
            swipe.setOnRefreshListener { refresh() }
            rv.adapter = categoryAdapter
        }
    }

    private fun refresh() {
        launch {
            newViewModel.getNewsData(
                query ?: "recommended",
                MySharedPreference(requireContext()).getPreferences("lang") ?: "en"
            )
                .collect {
                    when (it) {
                        is NewDataResource.Loading -> {
                            binding.swipe.isRefreshing = true
                        }
                        is NewDataResource.Error -> {
                            binding.rv.hide()
                            binding.swipe.isRefreshing = false
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        is NewDataResource.Success -> {
                            categoryAdapter.submitList(it.list)
                            binding.rv.show()
                            binding.swipe.isRefreshing = false
                        }
                    }
                }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    companion object {
        @JvmStatic
        fun newInstance(query: String) =
            CategoryPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, query)
                }
            }
    }
}