package com.ckunder.recyclersample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.cards_component.CardADLViewEntity
import com.ckunder.recyclersample2.adl.CardAdlViewComponent
import com.ckunder.recyclersample2.adl.CardAdlViewEntity
import com.ckunder.recyclersample2.adl.TwoLineAdlViewComponent
import com.ckunder.recyclersample2.adl.TwoLineAdlViewEntity
import com.ckunder.recyclersample2.adlAdapter.controller.AdlViewHolderController
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import com.ckunder.recyclersample2.genericAdapter.RecyclerViewAdapter
import com.ckunder.recyclersample2.genericAdapter.toDisplayableItem
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity2 : AppCompatActivity() {

    private val uiEntities: List<DisplayableItem<*>> = mutableListOf(
        TwoLineAdlViewEntity(title = "title1", subtitle = "subtitle1").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title3", subtitle = "subtitle3").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title4", subtitle = "subtitle4").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        CardAdlViewEntity(title = "Card Title", subtitle = "Card Subtitle").toDisplayableItem(CardAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title4", subtitle = "subtitle4").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title5", subtitle = "subtitle5").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title6", subtitle = "subtitle6").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title7", subtitle = "subtitle7").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title8", subtitle = "subtitle8").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title9", subtitle = "subtitle9").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title10", subtitle = "subtitle10").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title11", subtitle = "subtitle11").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title12", subtitle = "subtitle12").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title13", subtitle = "subtitle13").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title14", subtitle = "subtitle14").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title15", subtitle = "subtitle15").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title16", subtitle = "subtitle16").toDisplayableItem(TwoLineAdlViewComponent.layoutId)
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

        recyclerAdapter.update(uiEntities)
    }
}