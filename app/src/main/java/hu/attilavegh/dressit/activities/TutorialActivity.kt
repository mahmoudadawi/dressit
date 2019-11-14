package hu.attilavegh.dressit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import hu.attilavegh.dressit.R
import hu.attilavegh.dressit.utilities.SliderAdapter


class TutorialActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var slider: ViewPager
    private lateinit var tutorialStatus: LinearLayout

    private lateinit var sliderAdapter: SliderAdapter

    private lateinit var sliderStatusDots: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        supportActionBar!!.hide()

        slider = findViewById(R.id.slider)
        tutorialStatus = findViewById(R.id.tutorial_status)

        sliderAdapter = SliderAdapter(this)
        slider.adapter = sliderAdapter
        
        addDotsIndicator(0)
        slider.addOnPageChangeListener(this)
    }

    private fun addDotsIndicator(currentPosition: Int) {
        tutorialStatus.removeAllViews()
        sliderStatusDots = listOf(
            TextView(this),
            TextView(this),
            TextView(this)
        )

        sliderStatusDots.forEach { dot ->
            dot.text = Html.fromHtml("&#8226;")
            dot.textSize = 35f
            dot.setTextColor(resources.getColor(R.color.indicator_unselected))

            tutorialStatus.addView(dot)
        }

        sliderStatusDots[currentPosition].setTextColor(resources.getColor(R.color.indicator_selected))
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        addDotsIndicator(position)
    }

    override fun onPageSelected(position: Int) {
    }

}
