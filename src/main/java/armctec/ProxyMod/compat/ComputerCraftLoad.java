package armctec.ProxyMod.compat;

import dan200.computercraft.api.ComputerCraftAPI;

public class ComputerCraftLoad
{
    public static void init()
    {
        ComputerCraftAPI.registerBundledRedstoneProvider(new CCProvider());
    }
}
