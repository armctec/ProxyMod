package armctec.ProxyMod.handler;

import armctec.ProxyMod.reference.Reference;
import net.minecraftforge.common.Configuration;

import net.minecraftforge.common.Property;
import static net.minecraftforge.common.Property.Type.BOOLEAN;
import static net.minecraftforge.common.Property.Type.DOUBLE;
import static net.minecraftforge.common.Property.Type.INTEGER;
import static net.minecraftforge.common.Property.Type.STRING;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;
    public static boolean testValue = false;

    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        Property lido;
        lido = configuration.get("configValue", Configuration.CATEGORY_GENERAL, false, "This is an example configuration value");
        testValue = lido.getBoolean(false);

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}
