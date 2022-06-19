package com.morsecodetranslator.view.base

import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout

abstract class BaseActivity: AppCompatActivity() {


    fun toast(message: String) {
        toast("")
    }

//    private open fun setupBaseToolbarAndBackButton(
//        toolbar: Toolbar,
//        title: String? = null,
//        @DrawableRes iconBackButton: Int? = null,
//        appBar: AppBarLayout? = null,
//        @DrawableRes collapsedBackButton: Int? = null,
//        @DrawableRes expandedBackButton: Int? = null
//    ) {
//        setSupportActionBar(toolbar)
//        supportActionBar?.let { actionBar ->
//            actionBar.apply {
//                setDisplayHomeAsUpEnabled(true)
//                setDisplayShowTitleEnabled(title != null)
//                this.title = title
//                iconBackButton?.let { setHomeAsUpIndicator(it) }
//            }
//        }
//
//        appBar?.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
//            var scrollRange = -1
//            override fun onOffsetChanged(
//                appBarLayout: AppBarLayout,
//                verticalOffset: Int
//            ) { //Initialize the size of the scroll
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.totalScrollRange
//                }
//                //Check if the view is collapsed
//                if (scrollRange + verticalOffset == 0) {
//                    supportActionBar?.let { actionBar ->
//                        actionBar.apply {
//                            this.title = title
//                            collapsedBackButton?.let { setHomeAsUpIndicator(it) }
//                        }
//                    }
//                } else {
//                    supportActionBar?.let { actionBar ->
//                        actionBar.apply {
//                            this.title = ""
//                            expandedBackButton?.let { setHomeAsUpIndicator(it) }
//                        }
//                    }
//                }
//            }
//        })
//    }

//    fun setupBaseToolbarAndBackButton(
//        toolbar: Toolbar,
//        title: String?,
//        iconBackButton: Int?,
//        appBar: AppBarLayout?,
//        collapsedBackButton: Int?,
//        expandedBackButton: Int?
//    ) {
//        super.setupToolbarAndBackButton(
//            toolbar,
//            title,
//            iconBackButton,
//            appBar,
//            collapsedBackButton,
//            expandedBackButton
//        )
//        if (iconBackButton == null) supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_point_to_left)
//    }


}