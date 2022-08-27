package com.s0qva.easypunishment.config.category.moderator

import com.s0qva.easypunishment.client.util.minecraft.config.GuiConfigResourceUtil
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig
import net.minecraft.util.text.TextComponentString
import org.apache.logging.log4j.LogManager

object ModeratorCreationPunishmentReasonCategoryConfig {
    private const val CATEGORY_NAME = "5. Создание причины (модератор)"
    private val logger = LogManager.getLogger(javaClass);
    private lateinit var punishmentOptions: List<String>
    private var currentPunishmentOptionIndex = 0
    private var reason: String = ""
    private var reasonDescription: String = ""

    fun init() {
        punishmentOptions = GuiConfigResourceUtil.obtainPunishmentOptions()

        EasyPunishmentGuiConfig.category(CATEGORY_NAME) {
            subcategory("1. Форма заполнения") {
                selector(::currentPunishmentOptionIndex, "1. Вариант наказания",
                        "Выберите вариант наказания, для которого хотите создать причину",
                        options = punishmentOptions)
                paragraph(::reason, "2. Причина наказания",
                        "Например: Читы на BW")
                paragraph(::reasonDescription, "3. Пояснение причины наказания",
                        "Например: Использование читов на BedWars.")
            }
            subcategory("2. Подтверждение создания причины наказания") {
                button("Пожалуйста, подтвердите создание причины наказания",
                        "После подтверждения вы сможете найти созданную причину " +
                                "в разделе указанного вами варианта наказания",
                        "Создать") {
                    val selectedPunishment = punishmentOptions[currentPunishmentOptionIndex]

                    if(reason.isEmpty() || reasonDescription.isEmpty()) {
                        MinecraftUtil.sendMessageToPlayer(TextComponentString("Причина и пояснение не должны быть пустыми."))
                        return@button
                    }
                    if (selectedPunishment == "Бан") {
                        GuiConfigResourceUtil.saveBanReason(reason, reasonDescription)
                    } else if (selectedPunishment == "Мут") {
                        GuiConfigResourceUtil.saveMuteReason(reason, reasonDescription)
                    } else {
                        GuiConfigResourceUtil.saveKickReason(reason, reasonDescription)
                    }
                    reason = ""
                    reasonDescription = ""
                }
            }
        }
    }
}
