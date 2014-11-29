package armctec.ProxyMod.init;

import armctec.ProxyMod.block.BlockBasic;
//import armctec.ProxyMod.block.BlockRednetProxy;
import armctec.ProxyMod.block.BlockTileBasic;
import armctec.ProxyMod.handler.ConfigurationHandler;
import armctec.ProxyMod.integration.BlockProxyCCMFR;
import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;

public class ModBlocks
{
    public static BlockContainer ProxyCCMFR = null;

    public static void init()
    {
        ProxyCCMFR = new BlockProxyCCMFR(ConfigurationHandler.proxyccmfr_id);
        GameRegistry.registerBlock(ProxyCCMFR, Names.Blocks.PROXYCCMFR);

    }
}
