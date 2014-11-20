package armctec.ProxyMod.block;

import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.block.BlockBasic;

public class BlockRednetProxy extends BlockBasic
{
    public BlockRednetProxy(int id)
    {
        super(id);
        this.setBlockName(Names.Blocks.REDNETPROXY);
        this.setBlockTextureName(Names.Blocks.REDNETPROXY);
    }
}
