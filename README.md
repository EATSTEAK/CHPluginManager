# CHPluginManager
CommandHelper ported version of Bukkit's PluginManager

# Functions

## disable_plugin

**return**: void

**throws**: InvalidPluginException

**args**: PluginName

Disable a plugin named PluginName

PluginName 이라는 이름을 가진 플러그인을 비활성화 합니다.

## enable_plugin

**return**: void

**throws**: InvalidPluginException

**args**: PluginName

Enable a plugin named PluginName

PluginName 이라는 이름을 가진 플러그인을 활성화 합니다.

## load_plugin

**return**: void

**throws**: InvalidPluginException

**args**: FileName

Load a Plugin from file named FileName. FileName must be xxx.jar. and xxx.jar's path is inside of Bukkit's plugin folder.

FileName 이라는 이름을 가진 파일에서 플러그인을 로드합니다.

## plugin_is_enabled

**return**: boolean

**throws**: InvalidPluginException

**args**: PluginName

If Plugin enabled, return true.

PluginName 이라는 이름을 가진 플러그인이 활성화 되어 있다면, true 를 반환합니다.

## get_plugins

**return**: array

**throws**: none

**args**: none

get all plugin's array(includes disabled plugin)

모든 플러그인의 배열을 반환합니다.(비활성화된 플러그인 포함)

## get_plugin_description

**return**: array

**throws**: InvalidPluginException

**args**: PluginName

get plugin's description file(plugin.yml) array, key of array is same as plugin.yml's key

플러그인의 설명 파일(plugin.yml) 의 배열을 반환합니다. 배열의 키값은 plugin.yml 의 키값과 동일합니다.

# Download
[Click Here](http://github.com/itstake/CHPluginManager/releases)


