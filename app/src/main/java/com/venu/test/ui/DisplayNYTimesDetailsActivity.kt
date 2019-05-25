package com.venu.test.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.venu.test.R
import com.venu.test.utils.Constants
import kotlinx.android.synthetic.main.activity_display_nytimes_details.*

var name = ""
var authorName = ""
var summary = ""
var imageURL = "https://www.gstatic.com/webp/gallery/1.jpg"
class DisplayNYTimesDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_nytimes_details)
        setupToolbar()
        getIntentData()
        updateUI()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun getIntentData() {
        name=intent.extras.getString(Constants.INTENT_NAME)
        authorName=intent.extras.getString(Constants.INTENT_AUTHOR)
        summary=intent.extras.getString(Constants.INTENT_SUMMARY)
    }

    private fun updateUI() {
        //Displayed sample image url due to unavailability of image url in existing response.
        Glide
            .with(this)
            .load(imageURL)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageIV)
        titleTV.text = "Title: $name"
        authorTV.text = "Author: $authorName"
        summaryTV.text = "Summary: $summary"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            //Handle back button click navigation
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}