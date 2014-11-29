package armctec.ProxyMod.proxy;

import armctec.ProxyMod.integration.TileProviderCC;
import armctec.ProxyMod.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileProviderCC.class, Names.Blocks.PROXYCCMFR);
    }
}
