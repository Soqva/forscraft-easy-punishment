package com.s0qva.easypunishment.config.category.common

import com.s0qva.easypunishment.client.command.pardon.UnbanCommand
import com.s0qva.easypunishment.client.command.pardon.UnmuteCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import org.apache.logging.log4j.LogManager

object PardonCategoryConfig {
    private const val CATEGORY_NAME = "9. Снятие наказания"
    private val logger = LogManager.getLogger(javaClass);
    private var playerNickname: String = ""
    private var currentPardonOptionIndex = 0
    private lateinit var pardonOptions: List<String>

    fun init() {
        pardonOptions = GuiConfigResourceUtil.obtainPardonOptions()

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                selector(::currentPardonOptionIndex, "1. Вариант снятия наказания",
                    "Выберите вариант снятия наказания", options = pardonOptions)
                paragraph(::playerNickname, "2. Никнейм игрока", description = "Например: s0qva")
            }
            subcategory("2. Подтверждение cнятия наказания") {
                button("Пожалуйста, подтвердите снятие наказания указанному игроку",
                    "После снятия наказания вы несете полную ответственность за корректность этого снятия",
                    "Снять наказание") {
                    val pardonOption = pardonOptions[currentPardonOptionIndex].lowercase()

                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    if (pardonOption == "снять бан") {
                        CommandUtil.execute(UnbanCommand(playerNickname))
                    } else {
                        CommandUtil.execute(UnmuteCommand(playerNickname))
                    }
                    playerNickname = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            PardonCategoryConfig.playerNickname = playerNickname
        }
    }
}
