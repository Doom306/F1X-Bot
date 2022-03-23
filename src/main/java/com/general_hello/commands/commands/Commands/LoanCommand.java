package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.RpgUser.DataUtils;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class LoanCommand extends SlashCommand {
    public LoanCommand() {
        this.name = "loan";
        this.help = "Sends a loan application to the admins.";
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        if (DataUtils.checkEvent(event)) return;
        TextInput amount = TextInput.create("amount", "Amount of Money", TextInputStyle.PARAGRAPH)
                .setPlaceholder("How much money do you want to loan?")
                .setRequired(true)
                .setMinLength(1)
                .setMaxLength(10)
                .build();
        TextInput reason = TextInput.create("message", "Reason", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Why do you want to loan money?")
                .setRequired(true)
                .setMinLength(30)
                .setMaxLength(3900)
                .build();

        Modal modal = Modal.create("loan", "Loan Request")
                .addActionRows(ActionRow.of(amount), ActionRow.of(reason))
                .build();

        event.replyModal(modal).queue();
    }
}
