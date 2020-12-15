/*
 * This file is part of PlayerProfiles.
 *
 * PlayerProfiles is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlayerProfiles is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PlayerProfiles.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

/*
 * This file is part of PlayerProfiles.
 *
 * PlayerProfiles is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlayerProfiles is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PlayerProfiles.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

/*
 * This file is part of PlayerProfiles.
 *
 * PlayerProfiles is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlayerProfiles is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PlayerProfiles.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.kscott.playerprofiles.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import com.google.inject.Inject;
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
    @Inject
    public ProfileCommand(final @NonNull CommandManager<CommandSender> commandManager) {
        Command.Builder<CommandSender> profileCommand = commandManager.commandBuilder("profile");

        commandManager.command(profileCommand.handler(this::handleProfileCommand));
    }

    private void handleProfileCommand(final CommandContext<CommandSender> context) {
        final @NonNull CommandSender sender = context.getSender();
        sender.sendMessage("gnomed");
    }
}
