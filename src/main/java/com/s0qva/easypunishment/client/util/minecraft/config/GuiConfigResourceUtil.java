package com.s0qva.easypunishment.client.util.minecraft.config;

import com.google.common.collect.Lists;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import com.s0qva.easypunishment.client.util.basic.FileUtil;
import com.s0qva.easypunishment.client.util.basic.ReflectionUtil;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig;
import com.s0qva.easypunishment.config.category.moderator.ModeratorBanCategoryConfig;
import com.s0qva.easypunishment.config.category.moderator.ModeratorKickCategoryConfig;
import com.s0qva.easypunishment.config.category.moderator.ModeratorMuteCategoryConfig;
import gg.essential.vigilance.data.Category;
import gg.essential.vigilance.data.CategoryItem;
import gg.essential.vigilance.data.PropertyAttributesExt;
import gg.essential.vigilance.data.PropertyItem;
import gg.essential.vigilance.data.PropertyType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class GuiConfigResourceUtil {
    private static final String DEFAULT_EMPTY_REASON = "Пустое сообщение" + StringUtil.EQUAL_SIGN;
    private static final String GAME_DIRECTORY = MinecraftUtil.obtainGameDirectoryPathAsString();
    private static final String MOD_RESOURCES_DIRECTORY = "easy-punishment-resources";
    private static final String REASONS_DIRECTORY = "reasons";
    private static final String OPTIONS_DIRECTORY = "options";
    private static final String DONATER_DIRECTORY = "donater";
    private static final String MODERATOR_DIRECTORY = "moderator";
    private static final String BAN_DIRECTORY = "ban";
    private static final String MUTE_DIRECTORY = "mute";
    private static final String KICK_DIRECTORY = "kick";
    private static final List<String> BAN_RULE_POINTS = Lists.newArrayList(
            "1.0 Продажа аккаунта с донатом",
            "1.2 Порча игрового процесса",
            "1.3 Выдача наказаний без причины",
            "1.5 Строительство построек, вызывающие лаги",
            "1.6 Некорректное пояснение к указанной причине наказания",
            "2.1 Реклама сторонних ресурсов",
            "2.2 Торговля в игре за реальные деньги",
            "2.5 Обман игроков с целью их взлома",
            "3.0 Использование читов на мини-играх",
            "3.1 Намеренная порча игры своей команде",
            "3.2 Тимплей на соло режимах"
    );
    private static final Path BAN_RULE_POINTS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            REASONS_DIRECTORY,
            DONATER_DIRECTORY,
            BAN_DIRECTORY,
            "ban-rule-points.txt"
    );
    private static final List<String> MUTE_RULE_POINTS = Lists.newArrayList(
            "2.0 Неадекватное поведение в чате",
            "2.3 Флуд",
            "2.4 Попрошайничество доната",
            "2.6 Коверканье слов с целью обойти систему анти-мат"
    );
    private static final Path MUTE_RULE_POINTS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            REASONS_DIRECTORY,
            DONATER_DIRECTORY,
            MUTE_DIRECTORY,
            "mute-rule-points.txt"
    );
    private static final List<String> KICK_RULE_POINTS = Lists.newArrayList(
            "1.0 Продажа аккаунта с донатом",
            "1.1 Использование /sethome, /setwarp в чужом регионе",
            "1.2 Порча игрового процесса",
            "1.3 Выдача наказаний без причины",
            "1.4 Строительство половых органов рядом с чужим регионом",
            "1.5 Строительство построек, вызывающие лаги",
            "1.6 Некорректное пояснение к указанной причине наказания",
            "2.1 Реклама сторонних ресурсов",
            "2.2 Торговля в игре за реальные деньги",
            "2.3 Флуд",
            "2.4 Попрошайничество доната",
            "2.5 Обман игроков с целью их взлома",
            "3.0 Использование читов на мини-играх",
            "3.1 Намеренная порча игры своей команде",
            "3.2 Тимплей на соло режимах"
    );
    private static final Path KICK_RULE_POINTS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            REASONS_DIRECTORY,
            DONATER_DIRECTORY,
            KICK_DIRECTORY,
            "kick-rule-points.txt"
    );
    private static final List<String> PUNISHMENT_OPTIONS = Lists.newArrayList(
            "Бан",
            "Мут",
            "Кик"
    );
    private static final Path PUNISHMENT_OPTIONS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            OPTIONS_DIRECTORY,
            "punishment-options.txt"
    );
    private static final List<String> PARDON_OPTIONS = Lists.newArrayList(
            "Снять бан",
            "Снять мут"
    );
    private static final Path PARDON_OPTIONS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            OPTIONS_DIRECTORY,
            "pardon-options.txt"
    );
    private static final Map<String, String> BAN_REASONS = new LinkedHashMap<>();
    private static final Path BAN_REASONS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            REASONS_DIRECTORY,
            MODERATOR_DIRECTORY,
            BAN_DIRECTORY,
            "ban-reasons.txt"
    );
    private static final Map<String, String> KICK_REASONS = new LinkedHashMap<>();
    private static final Path KICK_REASONS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            REASONS_DIRECTORY,
            MODERATOR_DIRECTORY,
            KICK_DIRECTORY,
            "kick-reasons.txt"
    );
    private static final Map<String, String> MUTE_REASONS = new LinkedHashMap<>();
    private static final Path MUTE_REASONS_PATH = Paths.get(
            GAME_DIRECTORY,
            MOD_RESOURCES_DIRECTORY,
            REASONS_DIRECTORY,
            MODERATOR_DIRECTORY,
            MUTE_DIRECTORY,
            "mute-reasons.txt"
    );

    static {
        initializeConfigResources();
    }

    private static void initializeConfigResources() {
        if (FileUtil.notExists(BAN_RULE_POINTS_PATH)) {
            FileUtil.create(BAN_RULE_POINTS_PATH);
            FileUtil.writeCollectionWithNewLineAfter(BAN_RULE_POINTS_PATH, BAN_RULE_POINTS);
        }
        if (FileUtil.notExists(MUTE_RULE_POINTS_PATH)) {
            FileUtil.create(MUTE_RULE_POINTS_PATH);
            FileUtil.writeCollectionWithNewLineAfter(MUTE_RULE_POINTS_PATH, MUTE_RULE_POINTS);
        }
        if (FileUtil.notExists(KICK_RULE_POINTS_PATH)) {
            FileUtil.create(KICK_RULE_POINTS_PATH);
            FileUtil.writeCollectionWithNewLineAfter(KICK_RULE_POINTS_PATH, KICK_RULE_POINTS);
        }
        if (FileUtil.notExists(PUNISHMENT_OPTIONS_PATH)) {
            FileUtil.create(PUNISHMENT_OPTIONS_PATH);
            FileUtil.writeCollectionWithNewLineAfter(PUNISHMENT_OPTIONS_PATH, PUNISHMENT_OPTIONS);
        }
        if (FileUtil.notExists(PARDON_OPTIONS_PATH)) {
            FileUtil.create(PARDON_OPTIONS_PATH);
            FileUtil.writeCollectionWithNewLineAfter(PARDON_OPTIONS_PATH, PARDON_OPTIONS);
        }
        if (FileUtil.notExists(BAN_REASONS_PATH)) {
            FileUtil.create(BAN_REASONS_PATH);
            FileUtil.write(BAN_REASONS_PATH, DEFAULT_EMPTY_REASON);
        }
        if (FileUtil.notExists(KICK_REASONS_PATH)) {
            FileUtil.create(KICK_REASONS_PATH);
            FileUtil.write(KICK_REASONS_PATH, DEFAULT_EMPTY_REASON);
        }
        if (FileUtil.notExists(MUTE_REASONS_PATH)) {
            FileUtil.create(MUTE_REASONS_PATH);
            FileUtil.write(MUTE_REASONS_PATH, DEFAULT_EMPTY_REASON);
        }
    }

    public static List<String> obtainData(Path path) {
        return (List<String>) FileUtil.readAll(path);
    }

    public static List<String> obtainBanRulePoints() {
        return obtainData(BAN_RULE_POINTS_PATH);
    }

    public static List<String> obtainMuteRulePoints() {
        return obtainData(MUTE_RULE_POINTS_PATH);
    }

    public static List<String> obtainKickRulePoints() {
        return obtainData(KICK_RULE_POINTS_PATH);
    }

    public static List<String> obtainPunishmentOptions() {
        return obtainData(PUNISHMENT_OPTIONS_PATH);
    }

    public static List<String> obtainPardonOptions() {
        return obtainData(PARDON_OPTIONS_PATH);
    }

    public static Map<String, String> obtainReasons(Path filePath, Map<String, String> reasons) {
        if (!reasons.isEmpty()) {
            return reasons;
        }
        Collection<String> obtainedReasons = FileUtil.readAll(filePath);

        for (String obtainedReason : obtainedReasons) {
            if (StringUtil.isBlank(obtainedReason)) {
                continue;
            }
            String[] reasonAndItsDescription = StringUtil.split(obtainedReason, StringUtil.EQUAL_SIGN);
            String reason = reasonAndItsDescription[0];

            if (reasonAndItsDescription.length == 1) {
                reasons.put(reason, StringUtil.EMPTY);
            } else {
                String reasonDescription = reasonAndItsDescription[1];
                reasons.put(reason, reasonDescription);
            }
        }
        return reasons;
    }

    public static Map<String, String> obtainBanReasons() {
        return obtainReasons(BAN_REASONS_PATH, BAN_REASONS);
    }

    public static Map<String, String> obtainKickReasons() {
        return obtainReasons(KICK_REASONS_PATH, KICK_REASONS);
    }

    public static Map<String, String> obtainMuteReasons() {
        return obtainReasons(MUTE_REASONS_PATH, MUTE_REASONS);
    }
    public static List<String> obtainBanReasonsKeys() {
        return new ArrayList<>(obtainBanReasons().keySet());
    }

    public static List<String> obtainKickReasonsKeys() {
        return new ArrayList<>(obtainKickReasons().keySet());
    }

    public static List<String> obtainMuteReasonsKeys() {
        return new ArrayList<>(obtainMuteReasons().keySet());
    }

    public static void saveReason(Path path, Map<String, String> reasons, String reason, String reasonDescription) {
        String formattedReason = reason + StringUtil.EQUAL_SIGN + reasonDescription;

        FileUtil.writeWithNewLineBefore(path, formattedReason);
        reasons.put(reason, reasonDescription);
    }

    public static void saveBanReason(String reason, String reasonDescription) {
        saveReason(BAN_REASONS_PATH, BAN_REASONS, reason, reasonDescription);
        ModeratorBanCategoryConfig.INSTANCE.updateReasons();
    }

    public static void saveMuteReason(String reason, String reasonDescription) {
        saveReason(MUTE_REASONS_PATH, MUTE_REASONS, reason, reasonDescription);
        ModeratorMuteCategoryConfig.INSTANCE.updateReasons();
    }

    public static void saveKickReason(String reason, String reasonDescription) {
        saveReason(KICK_REASONS_PATH, KICK_REASONS, reason, reasonDescription);
        ModeratorKickCategoryConfig.INSTANCE.updateReasons();
    }

    public static void updateSelectorOptions(String categoryName, String selectorName, List<String> newOptions) {
        List<Category> categories = EasyPunishmentGuiConfig.INSTANCE.getCategories();

        for (Category category : categories) {
            if (!category.getName().equals(categoryName)) {
                continue;
            }
            List<CategoryItem> items = category.getItems();

            for (CategoryItem item : items) {
                if (!(item instanceof PropertyItem)) {
                    continue;
                }
                PropertyAttributesExt attributesExt = ((PropertyItem) item).getData().getAttributesExt();

                if (attributesExt.getType() != PropertyType.SELECTOR && !attributesExt.getName().equals(selectorName)) {
                    continue;
                }
                ReflectionUtil.setPrivateFinalValue(attributesExt, "options", newOptions);
            }
        }
    }
}
