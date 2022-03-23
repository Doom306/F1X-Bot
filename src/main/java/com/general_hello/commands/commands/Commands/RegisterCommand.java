package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.RpgUser.DataUtils;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

import java.util.Collections;

public class RegisterCommand extends SlashCommand {
    public RegisterCommand() {
        this.name = "register";
        this.help = "Register or make an account for a user.";
        this.cooldown = 10;
        this.options = Collections.singletonList(new OptionData(OptionType.USER, "user", "The user to make an account").setRequired(true));
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        if (DataUtils.checkMemberAdminCommand(event)) return;

        TextInput email = TextInput.create("role", "Role Name", TextInputStyle.SHORT)
                .setPlaceholder("Enter the Role name (Ex. Alfa Romeo - Team Principal)")
                .setRequired(true)
                .setRequiredRange(5, 50)
                .build();

        Modal modal = Modal.create("register:" + event.getOption("user").getAsUser().getId(), "Register")
                .addActionRows(ActionRow.of(email))
                .build();

        event.replyModal(modal).queue();
    }
}
