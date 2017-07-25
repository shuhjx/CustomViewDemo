package com.shuh.customviewdemos

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager
    private lateinit var tablayout : TabLayout

    private var pageModels : ArrayList<PageModel> = arrayListOf()

    init{
        pageModels.add(PageModel(R.layout.sample_color, R.string.title_draw_color, R.layout.practice_color)) //OK
        pageModels.add(PageModel(R.layout.sample_circle, R.string.title_draw_circle, R.layout.practice_circle)) //OK
        pageModels.add(PageModel(R.layout.sample_rect, R.string.title_draw_rect, R.layout.practice_rect)) //OK
        pageModels.add(PageModel(R.layout.sample_point, R.string.title_draw_point, R.layout.practice_point)) //OK
        pageModels.add(PageModel(R.layout.sample_oval, R.string.title_draw_oval, R.layout.practice_oval)) //OK
        pageModels.add(PageModel(R.layout.sample_line, R.string.title_draw_line, R.layout.practice_line)) //OK
        pageModels.add(PageModel(R.layout.sample_round_rect, R.string.title_draw_round_rect, R.layout.practice_round_rect)) //OK
        pageModels.add(PageModel(R.layout.sample_arc, R.string.title_draw_arc, R.layout.practice_arc)) //OK
        pageModels.add(PageModel(R.layout.sample_path, R.string.title_draw_path, R.layout.practice_path)) //OK
        pageModels.add(PageModel(R.layout.sample_histogram, R.string.title_draw_histogram, R.layout.practice_histogram)) //OK
        pageModels.add(PageModel(R.layout.sample_pie_chart, R.string.title_draw_pie_chart, R.layout.practice_pie_chart))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewpager)
        tablayout = findViewById(R.id.tablayout)


        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager){
            override fun getItem(position: Int) : PageFragment = PageFragment.newInstance(pageModels[position].sampleLayoutRes, pageModels[position].practiceLayoutRes)

            override fun getCount() : Int = pageModels.size

            override fun getPageTitle(position: Int): CharSequence = getString(pageModels[position].titleRes)

        }
        tablayout.setupWithViewPager(viewPager)

    }

    private data class PageModel (
         var sampleLayoutRes : Int,
         var titleRes : Int,
         var practiceLayoutRes : Int
    )

}
