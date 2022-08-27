package com.s0qva.easypunishment.config.category.moderator

import com.s0qva.easypunishment.client.command.punishment.moderator.ModeratorBanCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import org.apache.logging.log4j.LogManager

object ModeratorBanCategoryConfig {
    private const val CATEGORY_NAME = "1. Бан (модератор)"
    private val logger = LogManager.getLogger(javaClass);
    private var command: String = "fban"
    private var playerNickname: String = ""
    private var banTime: String = ""
    private lateinit var banReasonsKeys: List<String>
    private var currentBanReasonsKeyIndex = 0
    private lateinit var reasons: Map<String, String>
    private lateinit var reason: String

    fun init() {
        reasons = GuiConfigResourceUtil.obtainBanReasons() as LinkedHashMap<String, String>
        banReasonsKeys = GuiConfigResourceUtil.obtainBanReasonsKeys()
        reason = banReasonsKeys[currentBanReasonsKeyIndex]
        logger.info("REASONS: {}, SELECTOR: {}", reasons, banReasonsKeys)

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                text(::command, "1. Команда для бана (без /)", description = "Например: fban")
                paragraph(::playerNickname, "2. Никнейм нарушителя", description = "Например: s0qva")
                paragraph(::banTime, "3. Время бана (вместе с флагом)",
                    description = "Например: -t 10m\nP.S. ЕСЛИ ВРЕМЯ НЕ УКАЗАНО, ТО НАРУШИТЕЛЬ БУДЕТ ЗАБАНЕН НАВСЕГДА")
                selector(::currentBanReasonsKeyIndex, "4. Причина",
                        "Выберите причину наказания для бана, " +
                                "которую вы создали в разделе \"Создание причины\"",
                        options = banReasonsKeys)
            }
            subcategory("2. Подтверждение наказания") {
                button("Пожалуйста, подтвердите выдачу наказания указанному игроку",
                        "После выдачи наказания вы несете полную ответственность за корректность этого наказания",
                        "Забанить") {
                    val reasonKey = banReasonsKeys[currentBanReasonsKeyIndex]
                    reason = reasons.getValue(reasonKey)

                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    CommandUtil.execute(ModeratorBanCommand(command, playerNickname, banTime, reason))

                    playerNickname = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            ModeratorBanCategoryConfig.playerNickname = playerNickname
        }
    }

    fun updateReasons() {
        reasons = GuiConfigResourceUtil.obtainBanReasons() as LinkedHashMap<String, String>
        banReasonsKeys = GuiConfigResourceUtil.obtainBanReasonsKeys()
        GuiConfigResourceUtil.updateSelectorOptions(CATEGORY_NAME, "4. Причина", banReasonsKeys)
    }
}
