//package ru.myapp.pexels_app.adapter
//
//import android.content.res.Resources
//import androidx.fragment.app.Fragment
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import ru.myapp.pexels_app.bookmarks.presentation.BookmarkFragment
//import ru.myapp.pexels_app.home.presentation.HomeFragment
//import ru.myapp.pexels_app.parent.presentation.ParentFragment
//
//class PagerAdapter(parentFragment: ParentFragment) : FragmentStateAdapter(parentFragment) {
//    override fun getItemCount() = 2
//
//    override fun createFragment(position: Int): Fragment {
//        return when(position) {
//            0 -> { HomeFragment() }
//            1 -> { BookmarkFragment() }
//            else -> {throw Resources.NotFoundException("Position not fount")}
//        }
//    }
//}