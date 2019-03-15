package com.ckunder.recyclersample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.adl.TwoLineAdlViewEntity
import com.ckunder.recyclersample2.adlAdapter.controller.AdlViewHolderController
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import com.ckunder.recyclersample2.genericAdapter.RecyclerViewAdapter
import com.ckunder.recyclersample2.genericAdapter.toDisplayableItem
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity2 : AppCompatActivity() {

    private val uiEntities = mutableListOf(
        TwoLineAdlViewEntity(title = "title1", subtitle = "subtitle1"),
        TwoLineAdlViewEntity(title = "title3", subtitle = "subtitle3"),
        TwoLineAdlViewEntity(title = "title4", subtitle = "subtitle4"),
        TwoLineAdlViewEntity(title = "title4", subtitle = "subtitle4"),
        TwoLineAdlViewEntity(title = "title5", subtitle = "subtitle5"),
        TwoLineAdlViewEntity(title = "title6", subtitle = "subtitle6"),
        TwoLineAdlViewEntity(title = "title7", subtitle = "subtitle7"),
        TwoLineAdlViewEntity(title = "title8", subtitle = "subtitle8"),
        TwoLineAdlViewEntity(title = "title9", subtitle = "subtitle9"),
        TwoLineAdlViewEntity(title = "title10", subtitle = "subtitle10"),
        TwoLineAdlViewEntity(title = "title11", subtitle = "subtitle11"),
        TwoLineAdlViewEntity(title = "title12", subtitle = "subtitle12"),
        TwoLineAdlViewEntity(title = "title13", subtitle = "subtitle13"),
        TwoLineAdlViewEntity(title = "title14", subtitle = "subtitle14"),
        TwoLineAdlViewEntity(title = "title15", subtitle = "subtitle15"),
        TwoLineAdlViewEntity(title = "title16", subtitle = "subtitle16")
    )

    @Inject
    lateinit var controllerMap: Map<Int, @JvmSuppressWildcards AdlViewHolderController<*>>

    @Inject
    lateinit var comparator: ItemComparator

    private lateinit var recyclerAdapter: RecyclerViewAdapter

    private val component by lazy(LazyThreadSafetyMode.NONE) { DaggerMainActivity2Component.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        recyclerAdapter = RecyclerViewAdapter(comparator, controllerMap, controllerMap)
        with(recyclerView) {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }

        recyclerAdapter.update(uiEntities.toDisplayableItem(R.layout.two_line_view_component))
    }
}