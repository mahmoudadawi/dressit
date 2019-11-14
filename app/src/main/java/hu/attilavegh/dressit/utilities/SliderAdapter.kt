package hu.attilavegh.dressit.utilities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import hu.attilavegh.dressit.activities.InitialDataEntryActivity
import hu.attilavegh.dressit.R

class SliderAdapter(private val context: Context): PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    private val titles = listOf(
        "1. Choose a dress",
        "2. Point your camera",
        "3. Get started"
    )

    private val images = listOf(
        R.drawable.tutorial_image1,
        R.drawable.tutorial_image2,
        R.drawable.tutorial_image3
    )

    private val descriptions = listOf(
        "You can choose several dresses from our collection and try it on virtually.",
        "Check if the dress fits you in real time.",
        "Before you start using the application, please provide us some measurement for better experience."
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return titles.count()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.slide_layout, container, false)

        val tutorialTitle: TextView = view.findViewById(R.id.tutorial_title)
        val tutorialImage: ImageView = view.findViewById(R.id.tutorial_image)
        val tutorialDescription: TextView = view.findViewById(R.id.tutorial_description)
        val tutorialDone: Button = view.findViewById(R.id.tutorial_done)

        tutorialTitle.text = titles[position]
        tutorialImage.setImageResource(images[position])
        tutorialDescription.text = descriptions[position]

        if (position == 2) {
            tutorialDone.visibility  = View.VISIBLE
            tutorialDone.setOnClickListener {
                val intent = Intent(context, InitialDataEntryActivity::class.java)
                context.startActivity(intent)
            }
        } else {
            tutorialDone.visibility  = View.INVISIBLE
        }

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}