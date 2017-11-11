package org.openhab.binding.northq.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;

public class MockThing implements Thing {
    Map<String, String> properties = new HashMap<String, String>();

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLabel(String label) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Channel> getChannels() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @Nullable Channel getChannel(String channelId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ThingStatus getStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ThingStatusInfo getStatusInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setStatusInfo(ThingStatusInfo status) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHandler(ThingHandler thingHandler) {
        // TODO Auto-generated method stub

    }

    @Override
    public @Nullable ThingHandler getHandler() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @Nullable ThingUID getBridgeUID() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBridgeUID(ThingUID bridgeUID) {
        // TODO Auto-generated method stub

    }

    @Override
    public @NonNull Configuration getConfiguration() {
        // TODO Auto-generated method stub
        return new Configuration();
    }

    @Override
    public ThingUID getUID() {
        // TODO Auto-generated method stub
        return new ThingUID();
    }

    @Override
    public ThingTypeUID getThingTypeUID() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @NonNull Map<@NonNull String, String> getProperties() {
        // TODO Auto-generated method stub
        return properties;
    }

    @Override
    public String setProperty(@NonNull String name, String value) {
        properties.put(name, value);
        return null;
    }

    @Override
    public void setProperties(@NonNull Map<String, String> properties) {
        // TODO Auto-generated method stub

    }

    @Override
    public @Nullable String getLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLocation(String location) {
        // TODO Auto-generated method stub

    }

}
