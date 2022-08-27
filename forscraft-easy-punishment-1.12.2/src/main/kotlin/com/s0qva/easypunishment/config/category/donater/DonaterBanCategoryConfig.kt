package com.s0qva.easypunishment.config.category.donater

import com.s0qva.easypunishment.client.command.punishment.donater.DonaterBanCommand
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.client.util.minecraft.command.CommandUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import com.s0qva.easypunishment.config.category.common.PardonCategoryConfig
import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.LogManager

object DonaterBanCategoryConfig {
    private const val CATEGORY_NAME = "6. Бан (донатер)"
    private val logger = LogManager.getLogger(javaClass);
    private var playerNickname: String = ""
    private lateinit var banRulePoints: List<String>
    private var currentBanRulePointIndex = 0
    private var reasonDescription: String = ""

    fun init() {
        banRulePoints = GuiConfigResourceUtil.obtainBanRulePoints();
        logger.info("SELECTOR: {}", banRulePoints)

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                paragraph(::playerNickname, "1. Никнейм нарушителя", description = "Например: s0qva")
                selector(::currentBanRulePointIndex, "2. Пункт правил (причина бана)",
                        "Выберите пункт правил для выдачи бана", options = banRulePoints)
                paragraph(::reasonDescription, "3. Пояснение к указанной причине бана",
                        description = "Например: Выдача некорректного наказания.")
            }
            subcategory("2. Подтверждение наказания") {
                button("Пожалуйста, подтвердите выдачу наказания указанному игроку",
                        "После выдачи наказания вы несете полную ответственность за корректность этого наказания",
                        "Забанить") {
                    val banRulePoint = StringUtils.split(banRulePoints[currentBanRulePointIndex], " ")[0]

                    if (playerNickname.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.")
                        return@button
                    }
                    CommandUtil.execute(DonaterBanCommand(playerNickname, banRulePoint, reasonDescription))

                    playerNickname = ""
                    reasonDescription = ""

                    GuiConfigUtil.restoreChatScreen()
                }
            }
        }
    }

    fun passPlayerNickname(playerNickname: String) {
        if (playerNickname != " " && playerNickname != "") {
            DonaterBanCategoryConfig.playerNickname = playerNickname
        }
    }
}
