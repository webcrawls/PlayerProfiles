package live.webcrawls.profiles.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * The base Profile command
 */
public class ProfileCommand {

    /**
     * Constructs commands & subcommands for /profile
     *
     * @param commandManager CommandManager instance
     */
    public ProfileCommand(final @NonNull CommandManager<CommandSender> commandManager) {
        Command.Builder<CommandSender> profileCommand = commandManager.commandBuilder("profile");

        commandManager.command(profileCommand.handler(this::handleProfileCommand));
    }

    private void handleProfileCommand(final CommandContext<CommandSender> context) {
        final @NonNull CommandSender sender = context.getSender();
        sender.sendMessage("gnomed");
    }
}
