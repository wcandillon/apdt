package org.phpaspect.apdt.internal.core;

import org.eclipse.dltk.core.ScriptNature;
import org.phpaspect.apdt.core.APDTCorePlugin;

public class APDTNature extends ScriptNature{
    public static final String NATURE_ID = APDTCorePlugin.PLUGIN_ID + ".nature";
    public static final String CONTENT_TYPE = APDTCorePlugin.PLUGIN_ID + ".aspectsource";
}
