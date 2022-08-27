package com.s0qva.easypunishment.config.category.moderator

import com.s0qva.easypunishment.client.command.punishment.moderator.ModeratorBanCommand
import com.s0qva.easypunishment.client.command.punishment.moderator.ModeratorKickCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.LogManager

object ModeratorOwnReasonPunishmentCategoryConfig {
    private const val CATEGORY_NAME = "4. Свое наказание (модератор)"
    private val logger = LogManager.getLogger(javaClass);
    private var command: String = ""
    private var playerNickname: String = ""
    private var punishmentTime: String = ""
    private var reason: String = ""

    fun init() {
        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                text(::command, "1. Команда для наказания (без /)", description = "Например: fban")
                paragraph(::playerNickname, "2. Никнейм нарушителя", description = "Например: s0qva")
                paragraph(::punishmentTime, "3. Время наказания (вместе с флагом)",
                        description = "Например: -t 10m.\nP.S. ЕСЛИ СОБИРАЕТЕСЬ ВЫДАТЬ КИК, ТО ВРЕМЯ НЕ БУДЕТ УЧИТЫВАТЬСЯ")
                paragraph(::reason, "4. Причина", description = "Например: Выдача некорректного наказания.")
            }
            subcategory("2. Подтверждение наказания") {
                button("Пожалуйста, подтвердите выдачу наказания указанному игроку",
                        "После выдачи наказания вы несете полную ответственность за корректность этого наказания",
                        "Выдать наказание") {
                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    if (StringUtils.contains(command.lowercase(), "kick".lowercase())) {
                        CommandUtil.execute(ModeratorKickCommand(command, playerNickname, reason))
                    } else {
                        CommandUtil.execute(ModeratorBanCommand(command, playerNickname, punishmentTime, reason))
                    }
                    command = ""
                    playerNickname = ""
                    punishmentTime = ""
                    reason = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            ModeratorOwnReasonPunishmentCategoryConfig.playerNickname = playerNickname
        }
    }
}
