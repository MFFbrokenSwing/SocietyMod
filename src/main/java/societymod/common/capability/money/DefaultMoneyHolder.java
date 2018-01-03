package societymod.common.capability.money;

import societymod.common.money.IMoneyHolder;

public class DefaultMoneyHolder implements IMoneyHolder {

    private float money = 0;

    @Override
    public float getBalance() {
        return this.money;
    }

    @Override
    public void setBalance(final float newBalance) {
        this.money = newBalance;
    }

    @Override
    public float pushToBalance(final float amount, final boolean simulate) {
        final float newBalance = money + amount;
        if (!simulate)
            this.setBalance(newBalance);
        return newBalance;
    }

}
