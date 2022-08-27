package com.s0qva.easypunishment.config.category.donater

import com.s0qva.easypunishment.client.command.punishment.donater.DonaterKickCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.LogManager

object DonaterKickCategoryConfig {
    private const val CATEGORY_NAME = "8. Кик (донатер)"
    private val logger = LogManager.getLogger(javaClass);
    private var playerNickname: String = ""
    private lateinit var kickRulePoints: List<String>
    private var currentKickRulePointIndex = 0
    private var reasonDescription: String = ""

    fun init() {
        kickRulePoints = GuiConfigResourceUtil.obtainKickRulePoints();
        logger.info("SELECTOR: {}", kickRulePoints)

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                paragraph(::playerNickname, "1. Никнейм нарушителя", description = "Например: s0qva")
                selector(::currentKickRulePointIndex, "2. Пункт правил (причина кика)",
                        "Выберите пункт правил для выдачи кика", options = kickRulePoints)
                paragraph(::reasonDescription, "3. Пояснение к указанной причине кика",
                        description = "Например: Нахождение на чужой территории без согласия участника региона.")
            }
            subcategory("2. Подтверждение наказания") {
                button("Пожалуйста, подтвердите выдачу наказания указанному игроку",
                        "После выдачи наказания вы несете полную ответственность за корректность этого наказания",
                        "Кикнуть") {
                    val kickRulePoint = StringUtils.split(kickRulePoints[currentKickRulePointIndex], " ")[0]

                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    CommandUtil.execute(DonaterKickCommand(playerNickname, kickRulePoint, reasonDescription))

                    playerNickname = ""
                    reasonDescription = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            DonaterKickCategoryConfig.playerNickname = playerNickname
        }
    }
}
