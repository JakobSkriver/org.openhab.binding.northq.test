package org.openhab.binding.northq.internal.common;

import org.junit.Test;
import org.openhab.binding.northq.internal.NorthQHandlerFactory;

public class HandlerFactoryTest {
    @Test
    public void factoryTest() {
        NorthQHandlerFactory test = new NorthQHandlerFactory();
        // Mock these
        test.createHandler();
        test.supportsThingType(thingTypeUID);
        test.registerDiscoveryService(bridgeHandler);

    }
}
