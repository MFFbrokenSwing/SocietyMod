package societymod.common.money;

/**
 * This class will hold the money for someone or something
 *
 * @author BrokenSwing
 *
 */
public interface IMoneyHolder {

    public float getBalance();

    /**
     * Sets the balance for the holder.
     * 
     * @param newBalance
     *            The new balance of the player
     * @return the new balance of the player
     */
    public float setBalance(float newBalance);

    /**
     * Adds the specified amount of money in the holder's balance.
     *
     * @param amount
     *            The amount of money to add, can be negative
     * @param simulate
     *            Set it to true if you just want to simulate the operation
     * @return the new balance of the holder
     */
    public float pushToBalance(float amount, boolean simulate);

    /**
     * Removes the specified amount from the holder's balance
     *
     * @param amount
     *            The amount of money to remove, can be negative
     * @param simulate
     *            Set it to true if you just want to simulate the operation
     * @return the new balance of the holder
     */
    default public float pullFromBalance(final float amount, final boolean simulate) {
        return pushToBalance(-amount, simulate);
    }

}
