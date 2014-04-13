package com.ivan.nikolov.config

import com.typesafe.config.{ConfigSyntax, ConfigParseOptions, ConfigFactory, Config}
import java.io.File

/**
 * Created by volcom on 13/04/14.
 */
trait SettingsComponent {

  val settings: Settings

  class Settings(config: Config) {
    config.checkValid(ConfigFactory.defaultReference(), "scala-api")

    object Application {
      val port = config.getInt("scala-api.application.port")
    }
  }

  object Settings {
    /**
     * Creates settings that can exist in different environments.
     * @param configPath The path to the config file.
     * @param configName The name of the config file. The default is application.conf, as
     *                   the typesafe config recommendations are.
     * @return
     */
    def createSettings(configPath: String, configName: Option[String] = None): Settings = {
      val file = new File(configPath)
      if (file.exists()) {
        val fileConfig = ConfigFactory.parseFile(file, ConfigParseOptions.defaults().setSyntax(ConfigSyntax.CONF))
        new Settings(ConfigFactory.load(fileConfig))
      } else if (configName.isDefined) {
        new Settings(ConfigFactory.load(configName.get))
      } else {
        new Settings(ConfigFactory.load())
      }
    }
  }
}
