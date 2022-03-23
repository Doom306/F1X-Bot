package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.Objects.BotEmojis;
import com.general_hello.commands.commands.Commands.RpgUser.DataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AddCurrencyCommand extends SlashCommand {
    private final static DecimalFormat FORMATTER = DataUtils.formatter;
    public AddCurrencyCommand() {
        this.name = "addmoney";
        this.help = "Adds money to a user";
        this.children = new SlashCommand[]{new RaceIncome(), new DriverDeductions(), new PrizeMoney(), new LoanPayment(), new LoanRepayment()};
    }

    @Override
    protected void execute(SlashCommandEvent event) {}

    private static class RaceIncome extends SlashCommand {
        public RaceIncome() {
            this.name = "race_income";
            List<OptionData> options = new ArrayList<>();
            options.add(new OptionData(OptionType.USER, "user", "The user to give the money").setRequired(true));
            options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that money you'll give (Default is $100)").setRequired(false).setMinValue(1));
            this.options = options;
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            if (DataUtils.checkMemberAdminCommand(event)) return;
            if (DataUtils.checkEvent(event)) return;

            int money = 100;

            if (event.getOption("amount") != null) {
                money = (int) event.getOption("amount").getAsDouble();
            }

            RPGUser.addRaceIncome(event.getOption("user").getAsUser().getIdLong(), money);

            event.reply("Successfully added " + BotEmojis.currency + " " + FORMATTER.format(money) + " to " + event.getOption("user").getAsUser().getAsMention() + "\n" +
                    "Reason: "  + this.name).setEphemeral(true).queue();
        }
    }

    private static class DriverDeductions extends SlashCommand {
        public DriverDeductions() {
            this.name = "driver_deductions";
            List<OptionData> options = new ArrayList<>();
            options.add(new OptionData(OptionType.USER, "user", "The user to give the money").setRequired(true));
            options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that money you'll give (Default is $100)").setRequired(false).setMinValue(1));
            this.options = options;
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            if (DataUtils.checkMemberAdminCommand(event)) return;
            if (DataUtils.checkEvent(event)) return;

            int money = 100;

            if (event.getOption("amount") != null) {
                money = (int) event.getOption("amount").getAsDouble();
            }

            RPGUser.addDriverDeductions(event.getOption("user").getAsUser().getIdLong(), money);

            event.reply("Successfully added " + BotEmojis.currency + " " + FORMATTER.format(money) + " to " + event.getOption("user").getAsUser().getAsMention() + "\n" +
                    "Reason: "  + this.name).setEphemeral(true).queue();
        }
    }

    private static class PrizeMoney extends SlashCommand {
        public PrizeMoney() {
            this.name = "prize_money";
            List<OptionData> options = new ArrayList<>();
            options.add(new OptionData(OptionType.USER, "user", "The user to give the money").setRequired(true));
            options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that money you'll give (Default is $100)").setRequired(false).setMinValue(1));
            this.options = options;
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            if (DataUtils.checkMemberAdminCommand(event)) return;
            if (DataUtils.checkEvent(event)) return;

            int money = 100;

            if (event.getOption("amount") != null) {
                money = (int) event.getOption("amount").getAsDouble();
            }

            RPGUser.addPrizeMoney(event.getOption("user").getAsUser().getIdLong(), money);

            event.reply("Successfully added " + BotEmojis.currency + " " + FORMATTER.format(money) + " to " + event.getOption("user").getAsUser().getAsMention() + "\n" +
                    "Reason: "  + this.name).setEphemeral(true).queue();
        }
    }

    private static class LoanRepayment extends SlashCommand {
        public LoanRepayment() {
            this.name = "loan_repayment";
            List<OptionData> options = new ArrayList<>();
            options.add(new OptionData(OptionType.USER, "user", "The user to give the money").setRequired(true));
            options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that money you'll give (Default is $100)").setRequired(false).setMinValue(1));
            this.options = options;
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            if (DataUtils.checkMemberAdminCommand(event)) return;
            if (DataUtils.checkEvent(event)) return;

            int money = 100;

            if (event.getOption("amount") != null) {
                money = (int) event.getOption("amount").getAsDouble();
            }

            RPGUser.addLoanRepayment(event.getOption("user").getAsUser().getIdLong(), money);

            event.reply("Successfully added " + BotEmojis.currency + " " + FORMATTER.format(money) + " to " + event.getOption("user").getAsUser().getAsMention() + "\n" +
                    "Reason: "  + this.name).setEphemeral(true).queue();
        }
    }

    private static class LoanPayment extends SlashCommand {
        public LoanPayment() {
            this.name = "loan_payment";
            List<OptionData> options = new ArrayList<>();
            options.add(new OptionData(OptionType.USER, "user", "The user to give the money").setRequired(true));
            options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that money you'll give (Default is $100)").setRequired(false).setMinValue(1));
            this.options = options;
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            if (DataUtils.checkMemberAdminCommand(event)) return;
            if (DataUtils.checkEvent(event)) return;

            int money = 100;

            if (event.getOption("amount") != null) {
                money = (int) event.getOption("amount").getAsDouble();
            }

            RPGUser.addLoanPayment(event.getOption("user").getAsUser().getIdLong(), money);

            event.reply("Successfully added " + BotEmojis.currency + " " + FORMATTER.format(money) + " to " + event.getOption("user").getAsUser().getAsMention() + "\n" +
                    "Reason: "  + this.name).setEphemeral(true).queue();
        }
    }
}
