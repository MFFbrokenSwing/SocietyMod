package societymod.common.capability.person;

import java.util.List;

public interface IPerson {

    public List<String> getSocietiesIds();

    public void setSocietiesIds(List<String> ids);

    public boolean hasOpenedGuiMenu();

    public void setOpenedGuiMenu(boolean opened);

}
