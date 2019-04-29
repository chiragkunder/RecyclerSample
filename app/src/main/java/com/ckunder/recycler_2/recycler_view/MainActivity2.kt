package com.ckunder.recycler_2.recycler_view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ckunder.recycler_2.recycler_view.amount.GroupViewEntityImpl
import com.ckunder.recyclersample.R
import com.ckunder.recycler_2.recycler_view.amount.TwoLineADLViewEntity
import com.ckunder.recycler_2.recycler_view.amount.AmountListItemController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() {


    private val uiEntities = mutableListOf(
        GroupViewEntityImpl(
            items = mutableListOf(
                TwoLineADLViewEntity(
                    id = 1,
                    title = "title1",
                    subtitle = "subtitle1"
                ),
                TwoLineADLViewEntity(
                    id = 2,
                    title = "title2",
                    subtitle = "subtitle2"
                ),
                TwoLineADLViewEntity(
                    id = 3,
                    title = "title3",
                    subtitle = "subtitle3"
                ),
                TwoLineADLViewEntity(
                    id = 4,
                    title = "title4",
                    subtitle = "subtitle4"
                ),
                TwoLineADLViewEntity(
                    id = 5,
                    title = "title5",
                    subtitle = "subtitle5"
                ),
                TwoLineADLViewEntity(
                    id = 6,
                    title = "title6",
                    subtitle = "subtitle6"
                ),
                TwoLineADLViewEntity(
                    id = 7,
                    title = "title7",
                    subtitle = "subtitle7"
                )
            )
        ),
        GroupViewEntityImpl(
            items = mutableListOf(
                TwoLineADLViewEntity(
                    id = 8,
                    title = "title8",
                    subtitle = "subtitle8"
                ),
                TwoLineADLViewEntity(
                    id = 9,
                    title = "title9",
                    subtitle = "subtitle9"
                )
            )
        ),
        GroupViewEntityImpl(
            items = mutableListOf(
                TwoLineADLViewEntity(
                    id = 10,
                    title = "title10",
                    subtitle = "subtitle10"
                )
            )
        )
    )

    private val uiEntities2 = mutableListOf(
        GroupViewEntityImpl(
            items = mutableListOf(
                TwoLineADLViewEntity(
                    id = 2,
                    title = "title2",
                    subtitle = "subtitle2"
                ),
                TwoLineADLViewEntity(
                    id = 3,
                    title = "title3",
                    subtitle = "subtitle3"
                ),
                TwoLineADLViewEntity(
                    id = 4,
                    title = "title4",
                    subtitle = "subtitle4"
                ),
                TwoLineADLViewEntity(
                    id = 5,
                    title = "title5",
                    subtitle = "subtitle5"
                ),
                TwoLineADLViewEntity(
                    id = 6,
                    title = "title6",
                    subtitle = "subtitle6"
                ),
                TwoLineADLViewEntity(
                    id = 7,
                    title = "title7",
                    subtitle = "subtitle7"
                )
            )
        ),
        GroupViewEntityImpl(
            items = mutableListOf(
                TwoLineADLViewEntity(
                    id = 8,
                    title = "title8",
                    subtitle = "subtitle8"
                ),
                TwoLineADLViewEntity(
                    id = 9,
                    title = "title9",
                    subtitle = "subtitle9"
                )
            )
        ),
        GroupViewEntityImpl(
            items = mutableListOf(
                TwoLineADLViewEntity(
                    id = 10,
                    title = "title10",
                    subtitle = "subtitle10"
                )
            )
        )
    )


    private
    val recyclerViewAdapter = RecyclerViewAdapter(
        mapOf(
            TwoLineADLViewEntity::class to AmountListItemController()
        ) as ViewControllersMap
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        removeItem.background = RoundRectDrawableWithShadow(this.resources, Color.BLUE, 8f)
        removeItem.setOnClickListener {
            recyclerViewAdapter.updateList(uiEntities2)
        }
        addItem.setOnClickListener {
            recyclerViewAdapter.updateList(uiEntities)
        }

        with(recyclerView) {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(CardViewDecoration(context.resources, Color.WHITE))
            addItemDecoration(CardItemDecorationShad(context))
            addItemDecoration(GroupSpacingItemDecoration(20))
        }

        recyclerViewAdapter.updateList(uiEntities)

    }
}