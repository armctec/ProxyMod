package armctec.ProxyMod.proxy;

import armctec.ProxyMod.compat.MFRProvider;
import armctec.ProxyMod.integration.CCProvider;
import armctec.ProxyMod.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        //GameRegistry.registerTileEntity(CCProvider.class, Names.Blocks.BUNDLEDCC);
        //GameRegistry.registerTileEntity(MFRProvider.class, Names.Blocks.REDNETPROXY);
        GameRegistry.registerTileEntity(CCProvider.class, Names.Blocks.PROXYCCMFR);
    }
}
