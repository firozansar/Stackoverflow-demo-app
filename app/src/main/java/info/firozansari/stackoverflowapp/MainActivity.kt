package info.firozansari.stackoverflowapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.firozansari.stackoverflowapp.ui.main.MainFragment
import java.time.ZonedDateTime
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
        val date: Date = Date.from(ZonedDateTime.now().minusMonths(1).toInstant())
        val time: Long = date.time
    }
}