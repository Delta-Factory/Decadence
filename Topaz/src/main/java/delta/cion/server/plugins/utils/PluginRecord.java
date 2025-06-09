package delta.cion.server.plugins.utils;

public record PluginRecord(String id, String name, String mainClass, String[] depends, String[] softDepends) implements Plugin {}
