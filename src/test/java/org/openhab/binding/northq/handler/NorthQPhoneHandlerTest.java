// package org.openhab.binding.northq.handler;
//
// import java.util.ArrayList;
// import java.util.concurrent.TimeUnit;
//
// import org.eclipse.smarthome.core.thing.ChannelUID;
// import org.eclipse.smarthome.core.thing.Thing;
// import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.Mock;
// import org.openhab.binding.northq.internal.common.NorthQConfig;
// import org.openhab.binding.northq.internal.mock.MockCallback;
// import org.openhab.binding.northq.internal.mock.MockCommand;
// import org.openhab.binding.northq.internal.mock.MockThing;
// import org.openhab.binding.northq.internal.model.NorthNetwork;
// import org.openhab.binding.northq.internal.services.CredentialsService;
// import org.openhab.binding.northq.internal.services.NorthqServices;
//
// public class NorthQPhoneHandlerTest {
// NorthqServices services;
// CredentialsService credentialsServices;
// ArrayList<String> user;
// NorthNetwork network;
//
// private NorthQPhoneHandler handler;
// @Mock
// private Thing thing = new MockThing();
// @Mock
// private ThingHandlerCallback callback = new MockCallback();
// @Mock
// private MockCommand mockCommand = new MockCommand();
// @Mock
// private ChannelUID mockChannel;
//
// @Before
// public void setUp() throws Exception {
//
// services = new NorthqServices();
// credentialsServices = new CredentialsService();
// user = credentialsServices.getUserCredentials();
// network = services.mapNorthQNetwork(user.get(0), user.get(1));
//
// NorthQConfig.setNETWORK(network);
//
// thing.setProperty("thingID", "0"); // Test id for phone
// thing.setProperty("BINDING_ID", "northq"); // Always northq for the Binding_id
// thing.setProperty("ThingUID", "qPhone"); // Depends on the test (check NorthQBindingConstants)
// thing.setProperty("name", "Nicolaj");
//
// handler = new NorthQPhoneHandler(thing);
//
// handler.setCallback(callback);
//
// }
//
// @Test
// public void qPhoneChangeStatusTest() throws InterruptedException {
// handler.initialize();
// ChannelUID t = new ChannelUID("northq:qPhone:0:channelgps");
// TimeUnit.SECONDS.sleep(5);
//
// // Sending an On command to the phone
// handler.handleCommand(t, mockCommand);
// TimeUnit.SECONDS.sleep(5);
//
// // Sending an Off command to the phone
// mockCommand.command = "OFF";
// handler.handleCommand(t, mockCommand);
// TimeUnit.SECONDS.sleep(5);
//
// handler.handleRemoval();
// }
//
// }
