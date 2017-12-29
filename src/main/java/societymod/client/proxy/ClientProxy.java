package societymod.client.proxy;

import java.io.File;

import societymod.client.config.ClientConfig;
import societymod.common.config.BaseConfig;
import societymod.common.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

    @Override
    protected BaseConfig createConfig(final File file) {
        return new ClientConfig(file);
    }

}
