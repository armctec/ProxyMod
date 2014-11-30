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
    public static int proxyccmfr_id  = 1500;
    public static boolean vanillarecipe = false;

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
        lido = configuration.get("proxyccmfr_id", Configuration.CATEGORY_GENERAL, 1501, "ID para o bloco proxyccmfr");
        proxyccmfr_id = lido.getInt(1500);

        lido = configuration.get("recipe", Configuration.CATEGORY_GENERAL, false, "Utilizar Vanilla recipe?");
        vanillarecipe = lido.getBoolean(false);

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}
