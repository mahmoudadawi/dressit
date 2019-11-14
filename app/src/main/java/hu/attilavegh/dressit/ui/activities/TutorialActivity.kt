package hu.attilavegh.dressit.ui.activities

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

        sliderStatusDots = listOf(
            TextView(this),
            TextView(this),
            TextView(this)
        )

        sliderAdapter = SliderAdapter(this)
        slider.adapter = sliderAdapter
        
        addDotsIndicator()
        slider.addOnPageChangeListener(this)
    }

    private fun addDotsIndicator() {
        sliderStatusDots.forEach { dot ->
            dot.text = Html.fromHtml("&#8226;")
            dot.textSize = 35f
            dot.setTextColor(resources.getColor(R.color.indicator_unselected))

            tutorialStatus.addView(dot)
        }
    }

    private fun colorDots(currentPosition: Int) {
        sliderStatusDots.forEach { dot ->
            dot.setTextColor(resources.getColor(R.color.indicator_unselected))
        }

        sliderStatusDots[currentPosition].setTextColor(resources.getColor(R.color.indicator_selected))
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        colorDots(position)
    }

    override fun onPageSelected(position: Int) {
    }

}
