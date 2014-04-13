package com.ivan.nikolov.app

import com.ivan.nikolov.config.SettingsComponent

/**
 * Created by volcom on 13/04/14.
 */
trait TestEnvironment extends SettingsComponent {

  val settings = Settings.createSettings("", Some("test-application.conf"))
}
