package org.openhab.binding.northq.internal.common;

import java.util.ArrayList;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openhab.binding.northq.handler.MockNetworkHandler;
import org.openhab.binding.northq.handler.NorthQMotionHandler;
import org.openhab.binding.northq.handler.NorthQNetworkHandler;
import org.openhab.binding.northq.handler.NorthQPhoneHandler;
import org.openhab.binding.northq.handler.NorthQPlugHandler;
import org.openhab.binding.northq.handler.NorthQThermostatHandler;
import org.openhab.binding.northq.handler.GatewayHandler;
import org.openhab.binding.northq.internal.NorthQHandlerFactory;
import org.openhab.binding.northq.internal.mock.MockBridge;
import org.openhab.binding.northq.internal.mock.MockCallback;
import org.openhab.binding.northq.internal.mock.MockCommand;
import org.openhab.binding.northq.internal.mock.MockThing;
import org.openhab.binding.northq.internal.model.NorthNetwork;
import org.openhab.binding.northq.internal.services.CredentialsService;
import org.openhab.binding.northq.internal.services.NorthqServices;

public class HandlerFactoryTest {
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;

    private NorthQHandlerFactory handlerFactory;
    @Mock
    private ThingHandlerCallback callback = new MockCallback();
    @Mock
    public Thing thing = new MockThing();
    @Mock
    public Bridge bridge = new MockBridge();
    @Mock
    private MockCommand mockCommand = new MockCommand();
    @Mock
    private ChannelUID mockChannel;

    @Before
    public void setup() throws Exception {
        services = new NorthqServices();
        credentialsServices = new CredentialsService();
        user = credentialsServices.getUserCredentials();
        network = services.mapNorthQNetwork(user.get(0), user.get(1));

        NorthQConfig.setNETWORK(network);

        bridge.setProperty("username", user.get(0));
        bridge.setProperty("password", user.get(1));

        handlerFactory = new NorthQHandlerFactory();

        thing.setProperty("BINDING_ID", "northq");
        bridge.setProperty("BINDING_ID", "northq");

    }

    @Test
    public void networkThingTypeTest() {
        bridge.setProperty("ThingUID", "northqnetwork");
        // Creating a NetworkHandler
        NorthQNetworkHandler handler = (NorthQNetworkHandler) handlerFactory.createHandler(bridge);
        handler.setCallback(callback);
        handler.handleRemoval();
    }

    @Test
    public void plugThingTypeTest() {
        thing.setProperty("ThingUID", "qPlug");
        // Creating a PlugHandler
        NorthQPlugHandler handler = (NorthQPlugHandler) handlerFactory.createHandler(thing);
        handler.setCallback(callback);
        handler.handleRemoval();
    }

    @Test
    public void motionThingTypeTest() {
        thing.setProperty("ThingUID", "qMotion");
        // Creating a MotionHandler
        NorthQMotionHandler handler = (NorthQMotionHandler) handlerFactory.createHandler(thing);
        handler.setCallback(callback);
        handler.handleRemoval();
    }

    @Test
    public void thermostatThingTypeTest() throws InterruptedException {
        thing.setProperty("ThingUID", "qThermostat");
        // Creating a ThermostatHandler
        NorthQThermostatHandler handler = (NorthQThermostatHandler) handlerFactory.createHandler(thing);
        handler.setCallback(callback);
        handler.handleRemoval();

    }

    @Test
    public void phoneThingTypeTest() {
        thing.setProperty("ThingUID", "qPhone");
        handlerFactory.supportsThingType(thing.getThingTypeUID());
        // Creating a PhoneHandler
        NorthQPhoneHandler handler = (NorthQPhoneHandler) handlerFactory.createHandler(thing);
        handler.setCallback(callback);
        handler.handleRemoval();
    }

    @Test
    public void settingsThingTypeTest() {
        thing.setProperty("ThingUID", "settings");
        // Creating a SettingsHandler
        GatewayHandler handler = (GatewayHandler) handlerFactory.createHandler(thing);
        handler.setCallback(callback);
        handler.handleRemoval();
    }

    @Test
    public void mockThingTypeTest() {
        thing.setProperty("ThingUID", "mocknetwork");
        // Creating a MockNetworkHandler
        MockNetworkHandler handler = (MockNetworkHandler) handlerFactory.createHandler(thing);
        handler.setCallback(callback);
        handler.handleRemoval();
    }

    @Test
    public void nullThingTest() {
        thing.setProperty("ThingUID", "NULL");
        // Creating a none supported type handler
        handlerFactory.createHandler(thing);
    }
}
