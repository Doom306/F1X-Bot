package com.general_hello.commands.commands.Commands.RpgUser;

import com.general_hello.commands.Config;
import com.general_hello.commands.commands.Commands.Objects.BotEmojis;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicBoolean;

public class DataUtils {
    public static final DecimalFormat formatter = new DecimalFormat("#,###");

    public static String filter(String filterWord) {
        return filterWord.toLowerCase().replace("'", "").replaceAll("\\s+", "");
    }

    public static String getBarFromPercentage(int percentage) {
        String bar = "";

        if (percentage < 10) {
            bar = BotEmojis.bar1Empty + BotEmojis.bar2Empty + BotEmojis.bar2Empty + BotEmojis.bar3Empty;
        } else if (percentage < 20) {
            bar = BotEmojis.bar1Half + BotEmojis.bar2Empty + BotEmojis.bar2Empty + BotEmojis.bar3Empty;
        } else if (percentage < 30) {
            bar = BotEmojis.bar1Full + BotEmojis.bar2Empty + BotEmojis.bar2Empty + BotEmojis.bar3Empty;
        } else if (percentage < 40) {
            bar = BotEmojis.bar1Full + BotEmojis.bar2Half + BotEmojis.bar2Empty + BotEmojis.bar3Empty;
        } else if (percentage < 50) {
            bar = BotEmojis.bar1Full + BotEmojis.bar2High + BotEmojis.bar2Empty + BotEmojis.bar3Empty;
        } else if (percentage < 65) {
            bar = BotEmojis.bar1Full + BotEmojis.bar2Full + BotEmojis.bar2Half + BotEmojis.bar3Empty;
        } else if (percentage < 70) {
            bar = BotEmojis.bar1Full + BotEmojis.bar2Full + BotEmojis.bar2High + BotEmojis.bar3Empty;
        } else if (percentage < 85) {
            bar = BotEmojis.bar1Full + BotEmojis.bar2Full + BotEmojis.bar2Full + BotEmojis.bar3Half;
        } else if (percentage < 101) {
            bar = BotEmojis.bar1Full + BotEmojis.bar2Full + BotEmojis.bar2Full + BotEmojis.bar3Full;
        }

        return bar;
    }

    public static int getPercentage(int firstNumber, int secondNumber) {
        double solving = (double) firstNumber/secondNumber;
        solving = solving * 100;
        return (int) solving;
    }

    public static boolean checkIfAllowedToUseCommand(SlashCommandEvent event) {
        Member member = event.getMember();
        AtomicBoolean allowed = new AtomicBoolean(false);
        member.getRoles().forEach((role -> {
            if (role.getId().equals(Config.get("admina")) || role.getId().equals(Config.get("adminb")) || role.getId().equals(Config.get("adminc"))) {
                allowed.set(true);
            }
            if (role.getName().equalsIgnoreCase(getNameOfRoleByTextChannel(event.getTextChannel()))) {
                allowed.set(true);
            }
        }));

        return allowed.get();
    }

    public static boolean checkEvent(SlashCommandEvent event) {
        boolean cancel = false;
        if (!checkIfAllowedToUseCommand(event)) {
            event.reply("You are not an admin **or** you used the command in the wrong channel!").setEphemeral(true).queue();
            cancel = true;
        } else if (!isRegistered(event.getMember())) {
            event.reply("The admins haven't made you an account yet. Inform the admins or wait for them to make one for you.").setEphemeral(true).queue();
            cancel = true;
        }
        return cancel;
    }

    public static String getNameOfRoleByTextChannel(TextChannel textChannel) {
        return textChannel.getName().replace("bank", "").replaceAll("-", " ") + "- Team Principal";
    }

    public static boolean checkMemberAdminCommand(SlashCommandEvent event) {
        Member member = event.getMember();
        AtomicBoolean allowed = new AtomicBoolean(false);
        member.getRoles().forEach((role -> {
            if (role.getId().equals(Config.get("admina")) || role.getId().equals(Config.get("adminb")) || role.getId().equals(Config.get("adminc"))) {
                allowed.set(true);
            }
        }));

        if (!allowed.get()) {
            event.reply("The command you used is an admin only command!").setEphemeral(true).queue();
        }
        return !allowed.get();
    }

    public static boolean isRegistered(Member member) {
        return RPGUser.getLoanPayment(member.getIdLong()) != -1;
    }
}