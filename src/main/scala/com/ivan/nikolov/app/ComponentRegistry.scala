package com.ivan.nikolov.app

import com.ivan.nikolov.config.SettingsComponent

/**
 * The component registry that leverages the cake design pattern.
 */
object ComponentRegistry extends SettingsComponent {
  val settings = Settings.createSettings("/etc/ivan/nikolov/scala-api/config.properties")
}
