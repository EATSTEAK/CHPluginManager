package tk.itstake.chpluginmanager;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.*;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by ITSTAKE on 2015-12-26.
 */
public class Functions {
    public static PluginManager pluginManager = Bukkit.getPluginManager();
    public static String docs() {
        return "These functions contains some Bukkit PluginManager features. so you can enable/disable plugins and get other plugin's descriptions.";
    }

    @api
    public static class disable_plugin extends AbstractFunction {

        @Override
        public Exceptions.ExceptionType[] thrown() {
            return new Exceptions.ExceptionType[]{Exceptions.ExceptionType.InvalidPluginException};
        }

        @Override
        public boolean isRestricted() {
            return false;
        }

        @Override
        public Boolean runAsync() {
            return false;
        }

        @Override
        public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
            if(pluginManager.isPluginEnabled(args[0].getValue())) {
                pluginManager.disablePlugin(Bukkit.getPluginManager().getPlugin(args[0].getValue()));
                return CVoid.VOID;
            } else {
                throw new ConfigRuntimeException("That plugin is not exist", Exceptions.ExceptionType.InvalidPluginException, t);
            }
        }

        @Override
        public String getName() {
            return "disable_plugin";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "void {PluginName} Disable a plugin named 'PluginName'";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1,0,0);
        }
    }

    @api
    public static class plugin_is_enabled extends AbstractFunction {

        @Override
        public Exceptions.ExceptionType[] thrown() {
            return new Exceptions.ExceptionType[0];
        }

        @Override
        public boolean isRestricted() {
            return false;
        }

        @Override
        public Boolean runAsync() {
            return false;
        }

        @Override
        public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
            if(pluginManager.isPluginEnabled(args[0].getValue())) {
                return CBoolean.TRUE;
            } else {
                return CBoolean.FALSE;
            }
        }

        @Override
        public String getName() {
            return "plugin_is_enabled";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "boolean {PluginName} If plugin enabled, return true.";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1,0,0);
        }
    }

    @api
    public static class get_plugins extends AbstractFunction {

        @Override
        public Exceptions.ExceptionType[] thrown() {
            return new Exceptions.ExceptionType[0];
        }

        @Override
        public boolean isRestricted() {
            return false;
        }

        @Override
        public Boolean runAsync() {
            return false;
        }

        @Override
        public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
            CArray plugins = new CArray(t);
            for(Plugin p:pluginManager.getPlugins()) {
                plugins.push(new CString(p.getName(), t));
            }
            return plugins;
        }

        @Override
        public String getName() {
            return "get_plugins";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "array {} get all plugins array(includes disabled plugin)";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1,0,0);
        }
    }

    @api
    public static class enable_plugin extends AbstractFunction {

        @Override
        public Exceptions.ExceptionType[] thrown() {
            return new Exceptions.ExceptionType[]{Exceptions.ExceptionType.InvalidPluginException};
        }

        @Override
        public boolean isRestricted() {
            return false;
        }

        @Override
        public Boolean runAsync() {
            return false;
        }

        @Override
        public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
            String pluginName = args[0].getValue();
            Plugin pluginObject = null;
            for(Plugin plugin:pluginManager.getPlugins()) {
                if(plugin.getName().equals(pluginName)) {
                    pluginObject = plugin;
                }
            }
            if(pluginObject != null) {
                if(!pluginManager.isPluginEnabled(pluginObject)) {
                    Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(args[0].getValue()));
                    return CVoid.VOID;
                } else {
                    throw new ConfigRuntimeException("That plugin is already enabled", Exceptions.ExceptionType.InvalidPluginException, t);
                }
            } else {
                throw new ConfigRuntimeException("That plugin is not exist", Exceptions.ExceptionType.InvalidPluginException, t);
            }
        }

        @Override
        public String getName() {
            return "enable_plugin";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "void {PluginName} Enable a plugin named 'PluginName'";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1,0,0);
        }
    }

    @api
    public static class load_plugin extends AbstractFunction {

        @Override
        public Exceptions.ExceptionType[] thrown() {
            return new Exceptions.ExceptionType[]{Exceptions.ExceptionType.InvalidPluginException};
        }

        @Override
        public boolean isRestricted() {
            return false;
        }

        @Override
        public Boolean runAsync() {
            return false;
        }

        @Override
        public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
            String fileName = args[0].getValue();
            try {
                pluginManager.loadPlugin(new File(pluginManager.getPlugin("CommandHelper").getDataFolder().getParentFile().toPath() + File.pathSeparator + fileName));
                return CVoid.VOID;
            } catch (InvalidPluginException e) {
                throw new ConfigRuntimeException("Invaild Plugin File", Exceptions.ExceptionType.InvalidPluginException, t);
            } catch (InvalidDescriptionException e) {
                throw new ConfigRuntimeException("Invaild Description File", Exceptions.ExceptionType.InvalidPluginException, t);
            }
        }

        @Override
        public String getName() {
            return "load_plugin";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "void {FileName} Load a plugin from file named 'FileName', FileName must be xxx.jar. and plugin file must ";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1,0,0);
        }
    }

    @api
    public static class get_plugin_description extends AbstractFunction {

        @Override
        public Exceptions.ExceptionType[] thrown() {
            return new Exceptions.ExceptionType[]{Exceptions.ExceptionType.InvalidPluginException};
        }

        @Override
        public boolean isRestricted() {
            return false;
        }

        @Override
        public Boolean runAsync() {
            return false;
        }

        @Override
        public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
            String pluginName = args[0].getValue();
            Plugin pluginObject = null;
            PluginDescriptionFile dFile = null;
            CArray dArray = new CArray(t);
            for(Plugin plugin:pluginManager.getPlugins()) {
                if(plugin.getName().equals(pluginName)) {
                    pluginObject = plugin;
                }
            }
            if(pluginObject != null) {
                dFile = pluginObject.getDescription();
            } else {
                throw new ConfigRuntimeException("That plugin is not exist", Exceptions.ExceptionType.InvalidPluginException, t);
            }
            dArray.set(new CString("name", t), new CString(dFile.getName(), t), t);
            dArray.set(new CString("main", t), new CString(dFile.getMain(), t), t);
            dArray.set(new CString("version", t), new CString(dFile.getVersion(), t), t);
            if(dFile.getDescription() != null) {
                dArray.set(new CString("description", t), new CString(dFile.getDescription(), t), t);
            }
            if(dFile.getLoad() != null) {
                dArray.set(new CString("load", t), new CString(dFile.getLoad().toString(), t), t);
            }
            if(dFile.getAuthors() != null) {
                CArray authors = new CArray(t);
                for(String author:dFile.getAuthors()) {
                    authors.push(new CString(author, t));
                }
                dArray.set(new CString("authors", t), authors, t);
            }
            if(dFile.getWebsite() != null) {
                dArray.set(new CString("website", t), new CString(dFile.getWebsite(), t), t);
            }
            if(dFile.isDatabaseEnabled()) {
                dArray.set(new CString("database", t), CBoolean.TRUE, t);
            } else {
                dArray.set(new CString("database", t), CBoolean.FALSE, t);
            }
            if(dFile.getDepend() != null) {
                CArray depends = new CArray(t);
                for(String depend:dFile.getDepend()) {
                    depends.push(new CString(depend, t));
                }
                dArray.set(new CString("depend", t), depends, t);
            }
            if(dFile.getPrefix() != null) {
                dArray.set(new CString("prefix", t), new CString(dFile.getPrefix(), t), t);
            }
            if(dFile.getSoftDepend() != null) {
                CArray sdepends = new CArray(t);
                for(String sdepend:dFile.getSoftDepend()) {
                    sdepends.push(new CString(sdepend, t));
                }
                dArray.set(new CString("softdepend", t), sdepends, t);
            }
            if(dFile.getLoadBefore() != null) {
                CArray loadBefores = new CArray(t);
                for(String loadBefore:dFile.getLoadBefore()) {
                    loadBefores.push(new CString(loadBefore, t));
                }
                dArray.set(new CString("loadbefore", t), loadBefores, t);
            }
            if(dFile.getCommands() != null && dFile.getCommands().size() > 0) {
                Map<String, Map<String, Object>> commandsMap = dFile.getCommands();
                CArray commands = new CArray(t);
                for(String commandName:commandsMap.keySet()) {
                    Map<String, Object> commandMap = commandsMap.get(commandName);
                    CArray command = new CArray(t);
                    if(commandMap.keySet().contains("description")) {
                        command.set(new CString("description", t), new CString((String)commandMap.get("description"), t), t);
                    }
                    if(commandMap.keySet().contains("aliases") && commandMap.get("aliases") instanceof List && ((List<String>)commandMap.get("aliases")).size() > 0) {
                        CArray aliases = new CArray(t);
                        for(String alias:(List<String>)commandMap.get("aliases")) {
                            aliases.push(new CString(alias, t));
                        }
                        command.set(new CString("aliases", t), aliases, t);
                    }
                    if(commandMap.keySet().contains("aliases") && commandMap.get("aliases") instanceof String) {
                        command.set(new CString("aliases", t), new CString((String)commandMap.get("aliases"), t), t);
                    }
                    if(commandMap.keySet().contains("permission")) {
                        command.set(new CString("permission", t), new CString((String)commandMap.get("permission"), t), t);
                    }
                    if(commandMap.keySet().contains("permission-message")) {
                        command.set(new CString("permission-message", t), new CString((String)commandMap.get("permission-message"), t), t);
                    }
                    if(commandMap.keySet().contains("usage")) {
                        command.set(new CString("usage", t), new CString((String)commandMap.get("usage"), t), t);
                    }
                    commands.set(new CString(commandName, t), command, t);
                }
                dArray.set(new CString("commands", t), commands, t);
            }
            if(dFile.getPermissions() != null && dFile.getPermissions().size() > 0) {
                List<Permission> permissionsMap = dFile.getPermissions();
                CArray permissions = new CArray(t);
                for(Permission perm:permissionsMap) {
                    CArray permission = new CArray(t);
                    if(perm.getDescription() != null) {
                        permission.set(new CString("description", t), new CString(perm.getDescription(), t), t);
                    }
                    permission.set(new CString("default", t), new CString(getPermDefaultString(perm.getDefault()), t), t);
                    if(perm.getChildren() != null && perm.getChildren().size() > 0) {
                        CArray childrens = new CArray(t);
                        for(String children:perm.getChildren().keySet()) {
                            if(perm.getChildren().get(children)) {
                                childrens.set(new CString(children, t), CBoolean.TRUE, t);
                            } else {
                                childrens.set(new CString(children, t), CBoolean.FALSE, t);
                            }
                        }
                        permission.set(new CString("children", t), childrens, t);
                    }
                    permissions.set(new CString(perm.getName(), t), permission, t);
                }
                dArray.set(new CString("permissions", t), permissions, t);
            }
            return dArray;
        }

        private String getPermDefaultString(PermissionDefault permDefault) {
            if(permDefault.getValue(true) && permDefault.getValue(false)) {
                return "true";
            } else if(permDefault.getValue(true) && !permDefault.getValue(false)) {
                return "op";
            } else if(!permDefault.getValue(true) && permDefault.getValue(false)) {
                return "not op";
            } else {
                return "false";
            }
        }

        @Override
        public String getName() {
            return "get_plugin_description";
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        @Override
        public String docs() {
            return "array {PluginName} get plugin's description file(plugin.yml) array, It contains a key named ";
        }

        @Override
        public Version since() {
            return new SimpleVersion(1,0,0);
        }
    }
}
