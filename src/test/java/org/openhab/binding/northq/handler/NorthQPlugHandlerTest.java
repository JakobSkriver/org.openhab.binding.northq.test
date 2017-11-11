package org.openhab.binding.northq.handler;

import java.util.ArrayList;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
import org.mockito.Mock;
import org.openhab.binding.northq.internal.model.NorthNetwork;
import org.openhab.binding.northq.internal.services.CredentialsService;
import org.openhab.binding.northq.internal.services.NorthqServices;

public class NorthQPlugHandlerTest {
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;

    // private ThingHandler handler;

    private ThingTypeUID thingTypeUID = new ThingTypeUID("northq", "qPlug");

    @Mock
    private ThingHandlerCallback callback;

    @Mock
    private Thing thing;

    // @Mock
    // private Configuration configuration;
    /*
     * @Before
     * public void setUp() throws Exception {
     * initMocks(this);
     * 
     * when(thing.getThingTypeUID()).thenReturn(thingTypeUID);
     * 
     * // handler = new NorthQPlugHandler(thing);
     * // handler.setCallback(callback);
     * 
     * services = new NorthqServices();
     * credentialsServices = new CredentialsService();
     * user = credentialsServices.getUserCredentials();
     * network = services.mapNorthQNetwork(user.get(0), user.get(1));
     * 
     * }
     * 
     * @Test
     * public void plugOnHandlerTest() {
     * // Mockito.when(thing.getStatus()).thenReturn(handler.getThing().getStatus());
     * // NorthQConfig config = new NorthQConfig();
     * 
     * // Next 2 lines, either here or in setUp()
     * NorthQPlugHandler handler = spy(new NorthQPlugHandler(thing));
     * handler.setCallback(callback);
     * 
     * // I don't believe we need the next few lines. They are used for changing variables in NorthQBindingConstants
     * // when(thing.getConfiguration()).thenAnswer(a -> {
     * // Configuration conf = new Configuration();
     * // // conf.put(NorthQBindingConstants.CHANNEL_QPLUG, "channelPlug");
     * // return conf;
     * // });
     * 
     * // If the qPlug is connected to the qStick, handler.initialize() should set ThingStatus.ONLINE for qPlug
     * handler.initialize();
     * 
     * assertThat(handler.getThing().getStatus(), is(equalTo(ThingStatus.ONLINE)));
     * }
     * 
     * @Test
     * public void plugOffHandlerTest() {
     * assertTrue(true);
     * 
     * }
     */

}
