package com.example.prototype.mymoviecataloguemade5.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prototype.mymoviecataloguemade5.R


class NotificationSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_setting)

        supportActionBar?.title = resources.getString(R.string.title_notification_setting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .add(R.id.notification_holder, NotificationSettingFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
