package org.openhab.binding.northq.discovery;

import org.junit.Test;
import org.openhab.binding.northq.internal.discovery.NorthQDiscoveryService;
import org.openhab.binding.northq.internal.mock.NorthQMockNetwork;
import org.openhab.binding.northq.internal.model.NorthNetwork;

public class DiscoveryTest {
    @Test
    public void discoveryTest() {

        NorthQDiscoveryService ds = new NorthQDiscoveryService();
        ds.startScan();

        NorthQMockNetwork mocknetwork = new NorthQMockNetwork();
        NorthNetwork network = mocknetwork.getNetwork();

        ds.discoverAlldevices(network);
    }

}
