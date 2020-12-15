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

import com.google.inject.AbstractModule;
import dev.kscott.playerprofiles.VanadinitePlugin;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.Plugin;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * The module used to inject PlayerProfilesPlugin
 */
public class PluginModule extends AbstractModule {

    /**
     * The PlayerProfilesPlugin reference
     */
    private final @NonNull VanadinitePlugin plugin;

    /**
     * The Audiences instance
     */
    private final @NonNull BukkitAudiences audiences;

    /**
     * Constructs PluginModule
     *
     * @param plugin PlayerProfilesPlugin reference
     */
    public PluginModule(final @NonNull VanadinitePlugin plugin) {
        this.plugin = plugin;
        this.audiences = BukkitAudiences.create(plugin);
    }

    /**
     * Configures the Guice module
     */
    @Override
    public void configure() {
        this.bind(Plugin.class).toInstance(this.plugin);
        this.bind(VanadinitePlugin.class).toInstance(this.plugin);
        this.bind(BukkitAudiences.class).toInstance(this.audiences);
    }


}
