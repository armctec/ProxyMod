package armctec.ProxyMod.utility;

import armctec.ProxyMod.reference.Reference;
import cpw.mods.fml.common.FMLLog;
import java.util.logging.Level;

public class LogHelper
{
    public static void log(Level logLevel, Object object)
    {
        FMLLog.log(Reference.MOD_NAME, logLevel, String.valueOf(object));
    }

    public static void all(Object object)
    {
        log(Level.ALL, object);
    }

    public static void debug(Object object)
    {
        log(Level.WARNING, object);
    }

    public static void error(Object object)
    {
        log(Level.SEVERE, object);
    }

    public static void info(Object object)
    {
        log(Level.INFO, object);
    }

    public static void off(Object object)
    {
        log(Level.OFF, object);
    }

    public static void trace(Object object)
    {
        log(Level.FINEST, object);
    }

    public static void warn(Object object)
    {
        log(Level.WARNING, object);
    }
}
