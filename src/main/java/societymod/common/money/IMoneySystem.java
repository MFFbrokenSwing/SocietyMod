package societymod.common.money;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;

public interface IMoneySystem {

    /**
     * Returns the instance of {@link IMoneyBHolder} for the specified
     * {@link EntityPlayer}.
     *
     * @param player
     *            The {@link EntityPlayer}
     * @return the instance of {@link IMoneyHolder}, can't be null
     */
    @Nonnull
    public IMoneyHolder getHolderFor(EntityPlayer player);

    /**
     * Returns the instance of {@link IMoneyHolder} for the specified
     * {@link Object}.
     *
     * @param obj
     *            The {@link Object} to return the holder
     * @return the instance of {@link IMoneyHolder} or null
     */
    @Nullable
    default public IMoneyHolder getHolderFor(final Object obj) {
        if (obj instanceof EntityPlayer)
            return getHolderFor((EntityPlayer) obj);
        return null;
    }

    /**
     * Indicates the name to display of the amount of money
     *
     * @return the name of the money
     */
    public String getMoneyName();

}
