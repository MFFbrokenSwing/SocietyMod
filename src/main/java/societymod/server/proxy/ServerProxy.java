package societymod.server.proxy;

import java.io.File;

import societymod.common.config.BaseConfig;
import societymod.common.proxy.CommonProxy;
import societymod.server.config.ServerConfig;

public class ServerProxy extends CommonProxy {

    @Override
    protected BaseConfig createConfig(final File file) {
        return new ServerConfig(file);
    }

}
