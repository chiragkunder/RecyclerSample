package com.ckunder.recyclersample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.adl.CardAdlViewComponent
import com.ckunder.recyclersample2.adl.CardAdlViewEntity
import com.ckunder.recyclersample2.adl.TwoLineAdlViewComponent
import com.ckunder.recyclersample2.adl.TwoLineAdlViewEntity
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.RecyclerViewAdapter
import com.ckunder.recyclersample2.genericAdapter.controller.group.Group
import com.ckunder.recyclersample2.genericAdapter.controller.group.toDisplayableItem
import com.ckunder.recyclersample2.genericAdapter.toDisplayableItem
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity2 : AppCompatActivity() {

    // This can be done much more gracefully.
    // I will come up with a way to make it easy to wrap the adl view entities in displayable items that does not involve
    // repetition.
    private val uiEntities: List<DisplayableItem<*>> = mutableListOf(
        TwoLineAdlViewEntity(title = "title1", subtitle = "subtitle1").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title3", subtitle = "subtitle3").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title4", subtitle = "subtitle4").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TextViewEntity("This is some text view entity 1").toDisplayableItem(R.layout.item_text),
        CardAdlViewEntity(title = "Card Title", subtitle = "Card Subtitle").toDisplayableItem(CardAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title4", subtitle = "subtitle4").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        Group(
            displayableItemList = listOf(
                TwoLineAdlViewEntity(title = "grouped_title1", subtitle = "grouped_subtitle1").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
                TwoLineAdlViewEntity(title = "grouped_title2", subtitle = "grouped_subtitle2").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
                TwoLineAdlViewEntity(title = "grouped_title3", subtitle = "grouped_subtitle3").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
                TextViewEntity("GROUPED: This is some text view entity").toDisplayableItem(R.layout.item_text),
                TwoLineAdlViewEntity(title = "grouped_title4", subtitle = "grouped_subtitle4").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
                TwoLineAdlViewEntity(title = "grouped_title5", subtitle = "grouped_subtitle5").toDisplayableItem(TwoLineAdlViewComponent.layoutId)
            )
        ).toDisplayableItem(),
        TwoLineAdlViewEntity(title = "title5", subtitle = "subtitle5").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title6", subtitle = "subtitle6").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title7", subtitle = "subtitle7").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title8", subtitle = "subtitle8").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title9", subtitle = "subtitle9").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TextViewEntity("This is some text view entity 2").toDisplayableItem(R.layout.item_text),
        TwoLineAdlViewEntity(title = "title10", subtitle = "subtitle10").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title11", subtitle = "subtitle11").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title12", subtitle = "subtitle12").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title13", subtitle = "subtitle13").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title14", subtitle = "subtitle14").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title15", subtitle = "subtitle15").toDisplayableItem(TwoLineAdlViewComponent.layoutId),
        TwoLineAdlViewEntity(title = "title16", subtitle = "subtitle16").toDisplayableItem(TwoLineAdlViewComponent.layoutId)
    )

    @Inject
    lateinit var recyclerAdapter: RecyclerViewAdapter

    private val component by lazy(LazyThreadSafetyMode.NONE) { DaggerMainActivity2Component.builder().build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        with(recyclerView) {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }

        recyclerAdapter.update(uiEntities)
    }
}
