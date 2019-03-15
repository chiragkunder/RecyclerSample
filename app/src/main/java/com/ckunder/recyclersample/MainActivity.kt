package com.ckunder.recyclersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.cards_component.CardViewComponent
import com.ckunder.recyclersample.cards_component.CardADLViewEntity
import com.ckunder.recyclersample.group_component.GroupADLViewEntity
import com.ckunder.recyclersample.group_component.NestedRecyclerViewComponent
import com.ckunder.recyclersample.headline_component.TwoLineADLViewEntity
import com.ckunder.recyclersample.headline_component.TwoLineViewComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private val uiEntities = mutableListOf(
        TwoLineADLViewEntity(title = "title1", subtitle = "subtitle1"),
        TwoLineADLViewEntity(title = "title3", subtitle = "subtitle3"),
        CardADLViewEntity(title = "Card Title", subtitle = "Card Subtitle"),
        TwoLineADLViewEntity(title = "title4", subtitle = "subtitle4"),
        GroupADLViewEntity(
            items = listOf(
                TwoLineADLViewEntity(title = "grouped_title1", subtitle = "grouped_subtitle1"),
                TwoLineADLViewEntity(title = "grouped_title2", subtitle = "grouped_subtitle2"),
                TwoLineADLViewEntity(title = "grouped_title3", subtitle = "grouped_subtitle3"),
                TwoLineADLViewEntity(title = "grouped_title4", subtitle = "grouped_subtitle4"),
                TwoLineADLViewEntity(title = "grouped_title5", subtitle = "grouped_subtitle5")
            )
        ),
        TwoLineADLViewEntity(title = "title4", subtitle = "subtitle4"),
        TwoLineADLViewEntity(title = "title5", subtitle = "subtitle5"),
        TwoLineADLViewEntity(title = "title6", subtitle = "subtitle6"),
        TwoLineADLViewEntity(title = "title7", subtitle = "subtitle7"),
        TwoLineADLViewEntity(title = "title8", subtitle = "subtitle8"),
        TwoLineADLViewEntity(title = "title9", subtitle = "subtitle9"),
        TwoLineADLViewEntity(title = "title10", subtitle = "subtitle10"),
        TwoLineADLViewEntity(title = "title11", subtitle = "subtitle11"),
        TwoLineADLViewEntity(title = "title12", subtitle = "subtitle12"),
        TwoLineADLViewEntity(title = "title13", subtitle = "subtitle13"),
        TwoLineADLViewEntity(title = "title14", subtitle = "subtitle14"),
        TwoLineADLViewEntity(title = "title15", subtitle = "subtitle15"),
        TwoLineADLViewEntity(title = "title16", subtitle = "subtitle16")
    )

    private val childComponents = provideGroupComponents()
    private val childAdapter = RecyclerViewAdapter<ViewComponent<ADLViewEntity>, ViewHolder>(viewPool, childComponents)

    private val parentComponents = provideComponents()
    private val recyclerViewAdapter =
        RecyclerViewAdapter<ViewComponent<ADLViewEntity>, ViewHolder>(viewPool, parentComponents)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(recyclerView) {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
            setRecycledViewPool(viewPool)
        }

        recyclerViewAdapter.updateList(uiEntities)
    }

    private fun provideComponents(): Map<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>> {
        val delegates = HashMap<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>>()
        delegates[TwoLineADLViewEntity::class] = TwoLineViewComponent() as ViewComponent<ADLViewEntity>
        delegates[GroupADLViewEntity::class] =
                NestedRecyclerViewComponent(childAdapter) as ViewComponent<ADLViewEntity>
        delegates[CardADLViewEntity::class] = CardViewComponent() as ViewComponent<ADLViewEntity>
        return delegates
    }

    private fun provideGroupComponents(): Map<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>> {
        val delegates = HashMap<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>>()
        delegates[TwoLineADLViewEntity::class] = TwoLineViewComponent() as ViewComponent<ADLViewEntity>
        return delegates
    }
}
