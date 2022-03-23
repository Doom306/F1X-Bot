package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.RpgUser.DataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collections;

public class DeleteAccountCommand extends SlashCommand {
    public DeleteAccountCommand() {
        this.name = "delete";
        this.help = "Deletes an account for a user.";
        this.cooldown = 10;
        this.options = Collections.singletonList(new OptionData(OptionType.USER, "user", "The user to delete an account").setRequired(true));
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        if (DataUtils.checkMemberAdminCommand(event)) return;
        RPGUser.deleteInfo(event.getOption("user").getAsUser().getIdLong());
        event.reply("Successfully deleted the account!").setEphemeral(true).queue();
    }
}
