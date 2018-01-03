package societymod.common.capability.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultPerson implements IPerson {

    protected ArrayList<String> socitiesIds   = new ArrayList<>();
    protected boolean           openedGuiMenu = false;

    @Override
    public List<String> getSocietiesIds() {
        return Collections.unmodifiableList(this.socitiesIds);
    }

    @Override
    public void setSocietiesIds(final List<String> ids) {
        this.socitiesIds.clear();
        this.socitiesIds.addAll(ids);
    }

    @Override
    public boolean hasOpenedGuiMenu() {
        return this.openedGuiMenu;
    }

    @Override
    public void setOpenedGuiMenu(final boolean opened) {
        this.openedGuiMenu = opened;
    }

}
