package armctec.ProxyMod.init;

import armctec.ProxyMod.integration.TileProviderCC;
import armctec.ProxyMod.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init()
    {
        GameRegistry.registerTileEntity(TileProviderCC.class, Names.Blocks.PROXYCCMFR);
    }
}
