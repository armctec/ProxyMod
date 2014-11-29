package armctec.ProxyMod.init;

import armctec.ProxyMod.block.BlockBasic;
//import armctec.ProxyMod.block.BlockRednetProxy;
import armctec.ProxyMod.block.BlockTileBasic;
import armctec.ProxyMod.compat.BlockCCProvider;
import armctec.ProxyMod.compat.BlockMFRProvider;
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
    //public static BlockContainer BundledCC = null;
    //public static BlockContainer Rednetproxy = null;
    public static BlockContainer ProxyCCMFR = null;
    //public static int BundledCC_id  = 1500;
    //public static int rednetproxy_id  = 1501;
    public static int proxyccmfr_id  = 1502;

    public static void init()
    {
        //Rednetproxy = new BlockMFRProvider(rednetproxy_id);
        //GameRegistry.registerBlock(Rednetproxy, Names.Blocks.REDNETPROXY);

        //BundledCC = new BlockCCProvider(BundledCC_id);
        //GameRegistry.registerBlock(BundledCC, Names.Blocks.BUNDLEDCC);

        ProxyCCMFR = new BlockProxyCCMFR(proxyccmfr_id);
        GameRegistry.registerBlock(ProxyCCMFR, Names.Blocks.PROXYCCMFR);

    }
}
