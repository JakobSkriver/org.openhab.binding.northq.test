package org.openhab.binding.northq.handler;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;

public class MockCallback implements ThingHandlerCallback {

    @Override
    public void stateUpdated(@NonNull ChannelUID channelUID, @NonNull State state) {
        // TODO Auto-generated method stub

    }

    @Override
    public void postCommand(@NonNull ChannelUID channelUID, Command command) {
        // TODO Auto-generated method stub

    }

    @Override
    public void statusUpdated(@NonNull Thing thing, @NonNull ThingStatusInfo thingStatus) {
        // TODO Auto-generated method stub

    }

    @Override
    public void thingUpdated(@NonNull Thing thing) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configurationUpdated(@NonNull Thing thing) {
        // TODO Auto-generated method stub

    }

    @Override
    public void migrateThingType(@NonNull Thing thing, @NonNull ThingTypeUID thingTypeUID,
            Configuration configuration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void channelTriggered(@NonNull Thing thing, @NonNull ChannelUID channelUID, String event) {
        // TODO Auto-generated method stub

    }

}
