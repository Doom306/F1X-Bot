package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.Objects.BotEmojis;
import com.general_hello.commands.commands.Commands.RpgUser.DataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.Collections;

public class BalancesCommand extends SlashCommand {
    public BalancesCommand() {
        this.name = "balances";
        this.help = "Shows other people's balances. (Admin Command)";
        this.options = Collections.singletonList(new OptionData(OptionType.USER, "user", "The user to check the balance of").setRequired(true));
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        if (DataUtils.checkMemberAdminCommand(event)) return;
        User user = event.getOption("user").getAsUser();

        if (RPGUser.getLoanPayment(user.getIdLong()) == -1) {
            event.reply("The user doesn't have an account still.").setEphemeral(true).queue();
            return;
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        int raceIncome = RPGUser.getRaceIncome(user.getIdLong());
        int driverDeductions = RPGUser.getDriverDeductions(user.getIdLong());
        int prizeMoney = RPGUser.getPrizeMoney(user.getIdLong());
        int loanRepayment = RPGUser.getLoanRepayment(user.getIdLong());
        int loanPayment = RPGUser.getLoanPayment(user.getIdLong());
        int totalMoney = raceIncome - driverDeductions + prizeMoney + loanPayment - loanRepayment;
        if (totalMoney == 0) {
            event.reply("The user's current balance is at $0").queue();
            return;
        }
        embedBuilder.setColor(event.getGuild().getSelfMember().getColor());
        embedBuilder.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());
        DecimalFormat formatter = new DecimalFormat("#,###");
        embedBuilder.setTitle(user.getName() + "'s Balance").setTimestamp(OffsetDateTime.now());
        embedBuilder.setDescription(
                "**Race Income:** " + (BotEmojis.currency + formatter.format(raceIncome)) + " `(" + getPercent(raceIncome, totalMoney) + "%)`\n" +
                "**Driver Deductions:** " + (BotEmojis.currency + formatter.format(driverDeductions)) + " `(" + getPercent(driverDeductions, totalMoney) + "%)`\n" +
                "**Prize Money:** " + (BotEmojis.currency + formatter.format(prizeMoney)) + " `(" + getPercent(prizeMoney, totalMoney) + "%)`\n" +
                "**Loan Repayment:** " + (BotEmojis.currency + formatter.format(loanRepayment)) + " `(" + getPercent(loanRepayment, totalMoney) + "%)`\n" +
                "**Loan Payment:** " + (BotEmojis.currency + formatter.format(loanPayment)) + " `(" + getPercent(loanPayment, totalMoney) + "%)`\n" +
                "**Total Balance:** " + (BotEmojis.currency + formatter.format(totalMoney)) + "");
        embedBuilder.setFooter(null, "https://discord.com/assets/6c42ea9f3e233d9110e04dcd87db55da.svg");
        event.replyEmbeds(embedBuilder.build()).queue();
    }

    private double getPercent(int part, int total) {
        double percentage = (double) (part/total);
        percentage = percentage * 1000;
        return (double) ((int) percentage)/10;
    }
}
