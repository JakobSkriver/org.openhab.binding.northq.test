package org.openhab.binding.northq.internal.mock;

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
        return null;
    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public List<Channel> getChannels() {
        return null;
    }

    @Override
    public @Nullable Channel getChannel(String channelId) {
        return null;
    }

    @Override
    public ThingStatus getStatus() {
        return null;
    }

    @Override
    public ThingStatusInfo getStatusInfo() {
        return null;
    }

    @Override
    public void setStatusInfo(ThingStatusInfo status) {

    }

    @Override
    public void setHandler(ThingHandler thingHandler) {

    }

    @Override
    public @Nullable ThingHandler getHandler() {
        return null;
    }

    @Override
    public @Nullable ThingUID getBridgeUID() {
        return null;
    }

    @Override
    public void setBridgeUID(ThingUID bridgeUID) {

    }

    @Override
    public @NonNull Configuration getConfiguration() {
        Configuration config = new Configuration();
        config.put("name", properties.get("name"));
        return config;
    }

    @Override
    public ThingUID getUID() {
        return new ThingUID(properties.get("BINDING_ID"), properties.get("ThingUID"));
    }

    @Override
    public ThingTypeUID getThingTypeUID() {
        return new ThingTypeUID(properties.get("BINDING_ID"), properties.get("ThingUID"));
    }

    @Override
    public @NonNull Map<@NonNull String, @NonNull String> getProperties() {
        return properties;
    }

    @Override
    public String setProperty(@NonNull String name, String value) {
        properties.put(name, value);
        return null;
    }

    @Override
    public void setProperties(@NonNull Map<@NonNull String, @NonNull String> properties) {

    }

    @Override
    public @Nullable String getLocation() {
        return null;
    }

    @Override
    public void setLocation(String location) {

    }

}
