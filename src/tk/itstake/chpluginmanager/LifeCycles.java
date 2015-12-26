package tk.itstake.chpluginmanager;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import org.bukkit.Bukkit;

/**
 * Created by ITSTAKE on 2015-12-26.
 */
@MSExtension("CHPluginManager")
public class LifeCycles extends AbstractExtension {
    @Override
    public Version getVersion() {
        return new SimpleVersion(1,0,0);
    }

    @Override
    public void onStartup() {
        Bukkit.getConsoleSender().sendMessage("[CHPluginManager] CHPluginManager 1.0.0 Enabled.");
    }

    @Override
    public void onShutdown() {
        Bukkit.getConsoleSender().sendMessage("[CHPluginManager] CHPluginManager 1.0.0 Disabled.");
    }
}
