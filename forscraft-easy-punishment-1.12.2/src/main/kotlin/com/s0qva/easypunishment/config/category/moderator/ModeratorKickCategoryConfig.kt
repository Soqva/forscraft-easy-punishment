package com.s0qva.easypunishment.config.category.moderator

import com.s0qva.easypunishment.client.command.punishment.moderator.ModeratorKickCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import org.apache.logging.log4j.LogManager

object ModeratorKickCategoryConfig {
    private const val CATEGORY_NAME = "3. Кик (модератор)"
    private val logger = LogManager.getLogger(javaClass);
    private var command: String = "fkick"
    private var playerNickname: String = ""
    private var currentKickReasonsKeyIndex = 0
    private lateinit var kickReasonsKeys: List<String>
    private lateinit var reasons: Map<String, String>
    private lateinit var reason: String

    fun init() {
        reasons = GuiConfigResourceUtil.obtainKickReasons() as LinkedHashMap<String, String>
        kickReasonsKeys = GuiConfigResourceUtil.obtainKickReasonsKeys()
        reason = kickReasonsKeys[currentKickReasonsKeyIndex]
        logger.info("REASONS: {}, SELECTOR: {}", reasons, kickReasonsKeys)

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                text(::command, "1. Команда для кика (без /)", description = "Например: fkick")
                paragraph(::playerNickname, "2. Никнейм нарушителя", description = "Например: s0qva")
                selector(::currentKickReasonsKeyIndex, "3. Причина",
                        "Выберите причину наказания для кика, " +
                                "которую вы создали в разделе \"Создание причины\"",
                        options = kickReasonsKeys)
            }
            subcategory("2. Подтверждение наказания") {
                button("Пожалуйста, подтвердите выдачу наказания указанному игроку",
                        "После выдачи наказания вы несете полную ответственность за корректность этого наказания",
                        "Кикнуть") {
                    val reasonKey = kickReasonsKeys[currentKickReasonsKeyIndex]
                    reason = reasons.getValue(reasonKey)

                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    CommandUtil.execute(ModeratorKickCommand(command, playerNickname, reason))

                    playerNickname = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            ModeratorKickCategoryConfig.playerNickname = playerNickname
        }
    }

    fun updateReasons() {
        reasons = GuiConfigResourceUtil.obtainKickReasons()
        kickReasonsKeys = GuiConfigResourceUtil.obtainKickReasonsKeys()
        GuiConfigResourceUtil.updateSelectorOptions(CATEGORY_NAME, "3. Причина", kickReasonsKeys)
    }
}
