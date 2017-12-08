package org.openhab.binding.northq.internal.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.northq.handler.NorthQPlugHandler;

public class MockThing implements Thing {
    Map<String, String> properties = new HashMap<String, String>();

    @Override
    public @NonNull String getLabel() {
        return "";
    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public @NonNull List<Channel> getChannels() {
        List<Channel> list = new ArrayList<Channel>();
        return list;
    }

    @Override
    public @NonNull Channel getChannel(String channelId) {
        Channel channel = new Channel(null, channelId);
        return channel;
    }

    @Override
    public @NonNull ThingStatus getStatus() {
        return ThingStatus.ONLINE;
    }

    @Override
    public @NonNull ThingStatusInfo getStatusInfo() {
        ThingStatusInfo thingstatus = new ThingStatusInfo(ThingStatus.ONLINE, ThingStatusDetail.BRIDGE_OFFLINE, "");
        return thingstatus;
    }

    @Override
    public void setStatusInfo(ThingStatusInfo status) {

    }

    @Override
    public void setHandler(ThingHandler thingHandler) {

    }

    @Override
    public @NonNull ThingHandler getHandler() {
        return new NorthQPlugHandler(this);
    }

    @Override
    public @NonNull ThingUID getBridgeUID() {
        return new ThingUID();
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
    public @NonNull Map<@NonNull String, String> getProperties() {
        return properties;
    }

    @Override
    public String setProperty(@NonNull String name, String value) {
        properties.put(name, value);
        return "";
    }

    @Override
    public void setProperties(@NonNull Map<String, String> properties) {

    }

    @Override
    public @NonNull String getLocation() {
        return "";
    }

    @Override
    public void setLocation(String location) {

    }

}
