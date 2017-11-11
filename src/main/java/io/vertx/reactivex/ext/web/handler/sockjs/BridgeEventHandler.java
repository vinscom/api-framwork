package io.vertx.reactivex.ext.web.handler.sockjs;

import in.erail.common.FramworkConstants;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import java.util.List;

/**
 *
 * @author vinay
 */
public class BridgeEventHandler implements Handler<BridgeEvent> {

  private List<String> mAddressAllowedToRegister;
  private List<String> mAddressAllowedToRegisterRegex;

  @Override
  public void handle(BridgeEvent pEvent) {

    JsonObject rawMessage = pEvent.getRawMessage();
    String address = null;

    if (rawMessage != null) {
      address = rawMessage.getString(FramworkConstants.SockJS.BRIDGE_EVENT_RAW_MESSAGE_ADDRESS);
    }

    switch (pEvent.type()) {
      case PUBLISH:
        handlePublish(address, pEvent);
        break;
      case RECEIVE:
        handleRecieve(address, pEvent);
        break;
      case REGISTER:
        handleRegister(address, pEvent);
        break;
      case SEND:
        handleSend(address, pEvent);
        break;
      case SOCKET_CLOSED:
        handleSocketClose(pEvent);
        break;
      case SOCKET_CREATED:
        handleSocketCreated(pEvent);
        break;
      case SOCKET_IDLE:
        handleSocketIdle(pEvent);
        break;
      case SOCKET_PING:
        handleSocketPing(pEvent);
        break;
      case UNREGISTER:
        handleUnregister(address, pEvent);
        break;
    }

  }

  public void handleRegister(String pAddress, BridgeEvent pEvent) {
    if (mAddressAllowedToRegister.isEmpty() && mAddressAllowedToRegisterRegex.isEmpty()) {
      pEvent.complete(true);
      return;
    }

    if (!(matchAddress(pAddress) || matchAddressRegex(pAddress))) {
      pEvent.fail("Can't subscribe to topic : " + pAddress);
    } else {
      pEvent.complete(true);
    }
  }

  private boolean matchAddress(String pAddress) {
    return mAddressAllowedToRegister
            .stream()
            .anyMatch((allowedAddress) -> (pAddress.equals(allowedAddress)));
  }

  private boolean matchAddressRegex(String pAddress) {
    return mAddressAllowedToRegisterRegex
            .stream()
            .anyMatch((allowedAddress) -> (pAddress.matches(allowedAddress)));
  }

  public List<String> getAddressAllowedToRegister() {
    return mAddressAllowedToRegister;
  }

  public void setAddressAllowedToRegister(List<String> pAddressAllowedToRegister) {
    this.mAddressAllowedToRegister = pAddressAllowedToRegister;
  }

  public List<String> getAddressAllowedToRegisterRegex() {
    return mAddressAllowedToRegisterRegex;
  }

  public void setAddressAllowedToRegisterRegex(List<String> pAddressAllowedToRegisterRegex) {
    this.mAddressAllowedToRegisterRegex = pAddressAllowedToRegisterRegex;
  }

  public void handlePublish(String pAddress, BridgeEvent pEvent) {
    pEvent.complete(true);
  }

  public void handleRecieve(String pAddress, BridgeEvent pEvent) {
    pEvent.complete(true);
  }

  public void handleSend(String pAddress, BridgeEvent pEvent) {
    pEvent.complete(true);
  }

  public void handleSocketClose(BridgeEvent pEvent) {
    pEvent.complete(true);
  }

  public void handleSocketCreated(BridgeEvent pEvent) {
    pEvent.complete(true);
  }

  public void handleSocketIdle(BridgeEvent pEvent) {
    pEvent.complete(true);
  }

  public void handleSocketPing(BridgeEvent pEvent) {
    pEvent.complete(true);
  }

  public void handleUnregister(String pAddress, BridgeEvent pEvent) {
    pEvent.complete(true);
  }

}