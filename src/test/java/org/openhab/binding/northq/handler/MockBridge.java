package org.openhab.binding.northq.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.BridgeHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;

public class MockBridge implements Bridge {
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
    public @Nullable ThingUID getBridgeUID() {
        return new ThingUID(properties.get("BINDING_ID"), properties.get("ThingUID"));
    }

    @Override
    public void setBridgeUID(ThingUID bridgeUID) {

    }

    @Override
    public @NonNull Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.put("username", properties.get("username"));
        configuration.put("password", properties.get("password"));
        return configuration;
    }

    @Override
    public ThingUID getUID() {
        return null;
    }

    @Override
    public ThingTypeUID getThingTypeUID() {
        return new ThingTypeUID(properties.get("BINDING_ID"), properties.get("ThingUID"));
    }

    @Override
    public @NonNull Map<@NonNull String, String> getProperties() {
        return properties;
    }

    @Override
    public String setProperty(@NonNull String name, String value) {
        properties.put(name, value);
        return null;
    }

    @Override
    public void setProperties(@NonNull Map<String, String> properties) {

    }

    @Override
    public @Nullable String getLocation() {
        return null;
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public List<Thing> getThings() {
        return null;
    }

    @Override
    public BridgeHandler getHandler() {
        return null;
    }

}
