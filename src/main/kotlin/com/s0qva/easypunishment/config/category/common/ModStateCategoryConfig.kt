package com.s0qva.easypunishment.config.category.common

import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import org.apache.logging.log4j.LogManager

object ModStateCategoryConfig {
    private const val CATEGORY_NAME = "10. Состояние мода"
    private val logger = LogManager.getLogger(javaClass)
    private var isEnabled: Boolean = true

    fun init() {
        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Включение/выключение мода") {
                checkbox(::isEnabled, name = "Включить мод",
                    description = "Если галочка поставлена, то мод включен, иначе выключен.")
            }
        }
    }

    fun isEnabled(): Boolean {
        return isEnabled
    }
}
