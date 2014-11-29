package armctec.ProxyMod.proxy;

import armctec.ProxyMod.compat.CCProvider;
import armctec.ProxyMod.compat.MFRProvider;
import armctec.ProxyMod.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(CCProvider.class, Names.Blocks.BUNDLEDCC);
    }
}
