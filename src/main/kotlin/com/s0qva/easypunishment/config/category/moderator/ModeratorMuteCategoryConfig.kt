package com.s0qva.easypunishment.config.category.moderator

import com.s0qva.easypunishment.client.command.punishment.moderator.ModeratorMuteCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import org.apache.logging.log4j.LogManager

object ModeratorMuteCategoryConfig {
    private const val CATEGORY_NAME = "2. Мут (модератор)"
    private val logger = LogManager.getLogger(javaClass);
    private var command: String = "fmute"
    private var playerNickname: String = ""
    private var muteTime: String = ""
    private var currentMuteReasonsKeyIndex = 0
    private lateinit var muteReasonsKeys: List<String>
    private lateinit var reasons: Map<String, String>
    private lateinit var reason: String

    fun init() {
        reasons = GuiConfigResourceUtil.obtainMuteReasons() as LinkedHashMap<String, String>
        muteReasonsKeys = GuiConfigResourceUtil.obtainMuteReasonsKeys()
        reason = muteReasonsKeys[currentMuteReasonsKeyIndex]
        logger.info("REASONS: {}, SELECTOR: {}", reasons, muteReasonsKeys)

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                text(::command, "1. Команда для мута (без /)", description = "Например: fmute")
                paragraph(::playerNickname, "2. Никнейм нарушителя", description = "Например: s0qva")
                paragraph(::muteTime, "3. Время мута (вместе с флагом)",
                    description = "Например: -t 10m\nP.S. ЕСЛИ ВРЕМЯ НЕ УКАЗАНО, ТО НАРУШИТЕЛЬ БУДЕТ ЗАМУЧЕН НАВСЕГДА")
                selector(::currentMuteReasonsKeyIndex, "4. Причина",
                        "Выберите причину наказания для мута, " +
                                "которую вы создали в разделе \"Создание причины\"",
                        options = muteReasonsKeys)
            }
            subcategory("2. Подтверждение наказания") {
                button("Пожалуйста, подтвердите выдачу наказания указанному игроку",
                        "После выдачи наказания вы несете полную ответственность за корректность этого наказания",
                        "Замутить") {
                    val reasonKey = muteReasonsKeys[currentMuteReasonsKeyIndex]
                    reason = reasons.getValue(reasonKey)

                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    CommandUtil.execute(ModeratorMuteCommand(command, playerNickname, muteTime, reason))

                    playerNickname = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            ModeratorMuteCategoryConfig.playerNickname = playerNickname
        }
    }

    fun updateReasons() {
        reasons = GuiConfigResourceUtil.obtainMuteReasons()
        muteReasonsKeys = GuiConfigResourceUtil.obtainMuteReasonsKeys()
        GuiConfigResourceUtil.updateSelectorOptions(CATEGORY_NAME, "4. Причина", muteReasonsKeys)
    }
}
