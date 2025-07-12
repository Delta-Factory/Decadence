package delta.cion.server.plugins.utils;

import org.jetbrains.annotations.Nullable;

public record PluginRecord(
	String id,							// Plugin id [a-z0-9_-]: test_plugin
	String name,						// Plugin name [a-zA-Z0-9_-]: -_Test Plugin 01_-
	String mainClass, 					// Plugin main class: pepe.world.Main
	String[] depends,					// Plugin depends (Contains plugins ids): [pepe_world, test_plugin]
	String[] softDepends, 				// Plugin soft depends (Depends but not requed for plugin starting)
	@Nullable String commandPrefix, 	// Plugin command prefix (/prefix:command)
	boolean enableMsgList, 				// Enable messages.yml
	boolean apiStatus					// Enable api
) implements Plugin {}
