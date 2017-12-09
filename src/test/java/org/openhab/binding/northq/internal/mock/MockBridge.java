package org.openhab.binding.northq.internal.mock;

import java.util.ArrayList;
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
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.BridgeHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.northq.handler.NorthQNetworkHandler;

public class MockBridge implements Bridge {
    Map<String, String> properties = new HashMap<String, String>();

    @Override
    public @NonNull String getLabel() {
        return "";
    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public List<Channel> getChannels() {
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
        configuration.put("homelocation", properties.get("homelocation"));
        return configuration;
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
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String setProperty(@NonNull String name, String value) {
        properties.put(name, value);
        return "";
    }

    @Override
    public void setProperties(Map<String, String> properties) {

    }

    @Override
    public @NonNull String getLocation() {
        return "";
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public @NonNull List<Thing> getThings() {
        List<Thing> thingstemp = new ArrayList<Thing>();
        return thingstemp;
    }

    @Override
    public @NonNull BridgeHandler getHandler() {
        NorthQNetworkHandler handler = new NorthQNetworkHandler(this);
        return handler;
    }

}
