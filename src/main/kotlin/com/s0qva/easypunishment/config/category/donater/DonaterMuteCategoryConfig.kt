package com.s0qva.easypunishment.config.category.donater

import com.s0qva.easypunishment.client.command.punishment.donater.DonaterMuteCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.LogManager

object DonaterMuteCategoryConfig {
    private const val CATEGORY_NAME = "7. Мут (донатер)"
    private val logger = LogManager.getLogger(javaClass);
    private var playerNickname: String = ""
    private lateinit var muteRulePoints: List<String>
    private var currentMuteRulePointIndex = 0
    private var reasonDescription: String = ""

    fun init() {
        muteRulePoints = GuiConfigResourceUtil.obtainMuteRulePoints();
        logger.info("SELECTOR: {}", muteRulePoints)

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                paragraph(::playerNickname, "1. Никнейм нарушителя", description = "Например: s0qva")
                selector(::currentMuteRulePointIndex, "2. Пункт правил (причина мута)",
                        "Выберите пункт правил для выдачи мута", options = muteRulePoints)
                paragraph(::reasonDescription, "3. Пояснение к указанной причине мута",
                        description = "Например: Оскорбление.")
            }
            subcategory("2. Подтверждение наказания") {
                button("Пожалуйста, подтвердите выдачу наказания указанному игроку",
                        "После выдачи наказания вы несете полную ответственность за корректность этого наказания",
                        "Замутить") {
                    val muteRulePoint = StringUtils.split(muteRulePoints[currentMuteRulePointIndex], " ")[0]

                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    CommandUtil.execute(DonaterMuteCommand(playerNickname, muteRulePoint, reasonDescription))

                    playerNickname = ""
                    reasonDescription = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            DonaterMuteCategoryConfig.playerNickname = playerNickname
        }
    }
}
