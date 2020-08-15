package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val pager = findViewById<ViewPager2>(R.id.view_pager)
        pager.adapter =
            object : FragmentStateAdapter(this) {
                override fun getItemCount() = 1
                override fun createFragment(position: Int) =
                    PageFragment()
            }
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = "DUMMY $position"
        }.attach()
    }
}

class PageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)

        recyclerView.adapter = object : RecyclerView.Adapter<Holder>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ) = Holder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            )

            override fun getItemCount() = 100

            override fun onBindViewHolder(holder: Holder, position: Int) {
                holder.bind(position)
            }
        }
        view.postDelayed(1000) {
            recyclerView.scrollToPosition(12)
        }

        return view
    }
}


class Holder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_text)
        textView.text = "Item $position"
        if (position % 10 == 0) {
            textView.rootView.setBackgroundColor(Color.parseColor("#FF8800"))
        } else {
            textView.rootView.background = null
        }
    }
}
