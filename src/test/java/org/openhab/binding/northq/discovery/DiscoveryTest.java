package org.openhab.binding.northq.discovery;

import org.junit.Test;
import org.openhab.binding.northq.internal.common.NorthQConfig;
import org.openhab.binding.northq.internal.discovery.NorthQDiscoveryService;
import org.openhab.binding.northq.internal.mock.NorthQMockNetwork;
import org.openhab.binding.northq.internal.model.NorthNetwork;

public class DiscoveryTest {
    @Test
    public void discoveryTest() {

        NorthQDiscoveryService ds = new NorthQDiscoveryService();
        // Setting up the network to a mock network
        NorthQMockNetwork mocknetwork = new NorthQMockNetwork();
        NorthNetwork network = mocknetwork.getNetwork();
        NorthQConfig.setNETWORK(network);
        // Scan all devices for the network
        ds.discoverAlldevices(network);
    }

}
