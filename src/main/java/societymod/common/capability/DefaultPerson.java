package societymod.common.capability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultPerson implements IPerson {

    protected ArrayList<String> socitiesIds = new ArrayList<>();

    @Override
    public List<String> getSocietiesIds() {
        return Collections.unmodifiableList(this.socitiesIds);
    }

    @Override
    public void setSocietiesIds(final List<String> ids) {
        this.socitiesIds.clear();
        this.socitiesIds.addAll(ids);
    }

}
