package com.s0qva.easypunishment.config

import com.s0qva.easypunishment.config.category.common.ModStateCategoryConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import com.s0qva.easypunishment.config.category.donater.DonaterBanCategoryConfig
import com.s0qva.easypunishment.config.category.donater.DonaterKickCategoryConfig
import com.s0qva.easypunishment.config.category.donater.DonaterMuteCategoryConfig
import com.s0qva.easypunishment.config.category.moderator.*
import com.s0qva.easypunishment.config.sorting.GuiElementSortingBehavior
import gg.essential.vigilance.Vigilant
import java.io.File

object EasyPunishmentGuiConfig : Vigilant(
        File("./config/forscraft-easy-punishment-1.12.2.toml"),
        sortingBehavior = GuiElementSortingBehavior()
) {
    private val moderatorBanCategoryConfig = ModeratorBanCategoryConfig
    private val moderatorMuteCategoryConfig = ModeratorMuteCategoryConfig
    private val moderatorKickCategoryConfig = ModeratorKickCategoryConfig
    private val moderatorOwnReasonPunishmentCategoryConfig = ModeratorOwnReasonPunishmentCategoryConfig
    private val moderatorCreationPunishmentReasonCategoryConfig = ModeratorCreationPunishmentReasonCategoryConfig
    private val donaterBanCategoryConfig = DonaterBanCategoryConfig
    private val donaterMuteCategoryConfig = DonaterMuteCategoryConfig
    private val donaterKickCategoryConfig = DonaterKickCategoryConfig
    private val pardonCategoryConfig = PardonCategoryConfig
    private val modStateCategoryConfig = ModStateCategoryConfig

    init {
        initialize()
        moderatorBanCategoryConfig.init()
        moderatorMuteCategoryConfig.init()
        moderatorKickCategoryConfig.init()
        moderatorOwnReasonPunishmentCategoryConfig.init()
        moderatorCreationPunishmentReasonCategoryConfig.init()
        donaterBanCategoryConfig.init()
        donaterMuteCategoryConfig.init()
        donaterKickCategoryConfig.init()
        pardonCategoryConfig.init()
        modStateCategoryConfig.init()
    }

    fun passPlayerNickname(playerNickname: String) {
        moderatorBanCategoryConfig.passPlayerNickname(playerNickname)
        moderatorMuteCategoryConfig.passPlayerNickname(playerNickname)
        moderatorKickCategoryConfig.passPlayerNickname(playerNickname)
        moderatorOwnReasonPunishmentCategoryConfig.passPlayerNickname(playerNickname)
        donaterBanCategoryConfig.passPlayerNickname(playerNickname)
        donaterMuteCategoryConfig.passPlayerNickname(playerNickname)
        donaterKickCategoryConfig.passPlayerNickname(playerNickname)
        pardonCategoryConfig.passPlayerNickname(playerNickname)
    }

    fun isEnabled(): Boolean {
        return modStateCategoryConfig.isEnabled()
    }
}
