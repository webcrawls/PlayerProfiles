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

package dev.kscott.playerprofiles.inject;

import cloud.commandframework.CommandManager;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Function;

/**
 * Holds the cloud CommandManager
 */
public class CommandModule extends AbstractModule {

    /**
     * The Plugin reference
     */
    private final @NonNull Plugin plugin;

    /**
     * Constructs the CommandModule
     *
     * @param plugin Plugin reference
     */
    public CommandModule(final @NonNull Plugin plugin) {
        this.plugin = plugin;
    }

    @Provides
    @Singleton
    public CommandManager<CommandSender> provideCommandManager(final @NonNull BukkitAudiences audiences) {
        try {
            final @NonNull Function<CommandSender, CommandSender> mapper = Function.identity();
            final @NonNull PaperCommandManager<CommandSender> commandManager = new PaperCommandManager<>(
                    this.plugin,
                    AsynchronousCommandExecutionCoordinator.<CommandSender>newBuilder().build(),
                    mapper,
                    mapper
            );

            if (commandManager.queryCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
                commandManager.registerAsynchronousCompletions();
            }

            return commandManager;
        } catch (final Exception e) {
            throw new RuntimeException("Failed to initialize command manager.", e);
        }
    }

}
