package org.openhab.binding.northq.internal.common;

import org.eclipse.smarthome.core.thing.Thing;
import org.junit.Test;
import org.mockito.Mock;
import org.openhab.binding.northq.NorthQBindingConstants;
import org.openhab.binding.northq.internal.NorthQHandlerFactory;

public class HandlerFactoryTest {
    @Mock
    private Thing thing = new Thing();

    @Test
    public void factoryTest() {
        NorthQHandlerFactory test = new NorthQHandlerFactory();
        // Mock these
        test.createHandler(thing);
        test.supportsThingType(NorthQBindingConstants.THING_TYPE_QTHERMOSTAT);
        test.registerDiscoveryService(null);

    }
}
