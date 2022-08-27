package com.s0qva.easypunishment.client.util.minecraft.chat;

import com.google.common.collect.Sets;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import com.s0qva.easypunishment.client.util.basic.CharacterUtil;
import com.s0qva.easypunishment.client.util.basic.ReflectionUtil;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class ChatMessageUtil {
    public static final Set<String> REMOVABLE_CHARACTERS = Sets.newHashSet(
            StringUtil.LEFT_SQUARE_BRACKET,
            StringUtil.RIGHT_SQUARE_BRACKET,
            StringUtil.COMMA,
            StringUtil.DOT,
            StringUtil.LEFT_BRACE,
            StringUtil.RIGHT_BRACE,
            StringUtil.EXCLAMATION_MARK
    );
    public static final String FORMATTED_CHAT_MESSAGE_REGEX = "§[0-9a-zA-Z]";
    private static final Logger LOGGER = LogManager.getLogger(ChatMessageUtil.class);

    private ChatMessageUtil() {
    }

    public static String obtainUnformattedMessageText(ITextComponent message) {
        if (isNull(message)) {
            LOGGER.warn("Received ITextComponent message is null");
            return StringUtil.EMPTY;
        }
        return obtainUnformattedMessageText(message.getUnformattedText());
    }

    public static String obtainUnformattedMessageText(String text) {
        if (StringUtil.isBlank(text)) {
            LOGGER.warn("Received message text is blank");
            return StringUtil.EMPTY;
        }
        return text.replaceAll(FORMATTED_CHAT_MESSAGE_REGEX, StringUtil.EMPTY);
    }

    public static String obtainSelectedChatWord(GuiNewChat chat, int xSelectedCord, int ySelectedCord) {
        if (!chat.getChatOpen()) {
            return StringUtil.EMPTY;
        }
        List<ChatLine> drawnChatLines = ReflectionUtil.obtainList(chat, ChatLine.class, "field_146253_i");
        Integer scrollPosition = ReflectionUtil.obtainInteger(chat, "field_146250_j");

        if (Objects.isNull(drawnChatLines) || Objects.isNull(scrollPosition)) {
            return StringUtil.EMPTY;
        }
        ScaledResolution resolution = new ScaledResolution(MinecraftUtil.MINECRAFT);
        FontRenderer fontRenderer = MinecraftUtil.obtainFontRender();
        float chatScale = chat.getChatScale();
        int scaleFactor = resolution.getScaleFactor();
        int selectedMessageWidth = MathHelper.floor((float) (xSelectedCord / scaleFactor - 2) / chatScale);
        int selectedMessageHeight = MathHelper.floor((float) (ySelectedCord / scaleFactor - 40) / chatScale);

        if (selectedMessageWidth < 0 || selectedMessageHeight < 0) {
            return StringUtil.EMPTY;
        }
        int minimalHeight = Math.min(chat.getLineCount(), drawnChatLines.size());

        if (selectedMessageWidth > MathHelper.floor((float) chat.getChatWidth() / chat.getChatScale())
                || selectedMessageHeight >= fontRenderer.FONT_HEIGHT * minimalHeight + minimalHeight) {
            return StringUtil.EMPTY;
        }
        int selectedMessageLinePosition = selectedMessageHeight / fontRenderer.FONT_HEIGHT + scrollPosition;

        if (selectedMessageLinePosition < 0 || selectedMessageLinePosition >= drawnChatLines.size()) {
            return StringUtil.EMPTY;
        }
        ChatLine chatline = drawnChatLines.get(selectedMessageLinePosition);
        ITextComponent chatMessage = chatline.getChatComponent();

        return obtainCertainChatWord(chatMessage, selectedMessageWidth);
    }

    public static String obtainCertainChatWord(ITextComponent message, int maxMessageWidth) {
        String unformattedMessageText = obtainUnformattedMessageText(message);
        return obtainCertainChatWord(unformattedMessageText, maxMessageWidth);
    }

    public static String obtainCertainChatWord(String text, int maxMessageWidth) {
        if (StringUtil.isBlank(text) || maxMessageWidth < 0) {
            LOGGER.warn("Received message text is blank or maxMessageWidth is negative number");
            return StringUtil.EMPTY;
        }
        FontRenderer fontRenderer = MinecraftUtil.obtainFontRender();
        char[] textChars = text.toCharArray();
        int desiredCharIndex = 0;
        int characterWidthSum = 0;

        for (char currentChar : textChars) {
            desiredCharIndex++;
            characterWidthSum += fontRenderer.getCharWidth(currentChar);

            if (characterWidthSum >= maxMessageWidth) {
                desiredCharIndex--;
                break;
            }
        }
        int rightSpaceCharacterIndex = text.indexOf(CharacterUtil.SPACE_CHARACTER, desiredCharIndex);
        int leftSpaceCharacterIndex = desiredCharIndex;

        try {
            while (leftSpaceCharacterIndex >= 0 && text.charAt(leftSpaceCharacterIndex) != CharacterUtil.SPACE_CHARACTER) {
                leftSpaceCharacterIndex--;
            }
            desiredCharIndex = leftSpaceCharacterIndex + 1;
            String unformattedChatWord = StringUtil.substring(text, desiredCharIndex, rightSpaceCharacterIndex);
            String formattedChatWord = StringUtil.EMPTY;

            for (String removableCharacter : REMOVABLE_CHARACTERS) {
                formattedChatWord = StringUtil.replaceAll(unformattedChatWord, removableCharacter, StringUtil.EMPTY);
                unformattedChatWord = formattedChatWord;
            }
            return formattedChatWord;
        } catch (StringIndexOutOfBoundsException exception) {
            LOGGER.error("An exception occurred: {}", exception.getMessage());
            return StringUtil.EMPTY;
        }
    }

    public static boolean isNull(ITextComponent message) {
        return Objects.isNull(message);
    }
}
