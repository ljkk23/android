
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

/**
 * Created by ghx on 2022/3/24.
 */
class NewsAdapter(act: FragmentActivity): FragmentStateAdapter(act) {

    private val category = arrayListOf<String>()

    override fun getItemCount(): Int {
        return category.size
    }

    override fun createFragment(position: Int): Fragment {
        val item = getItem(position)

        return NewsListFragment.newInstance(item)
    }

    fun setData(array: ArrayList<String>) {
        category.addAll(array)
        notifyDataSetChanged()
    }

    fun getItem(pos: Int): String {
        return category[pos]
    }

}