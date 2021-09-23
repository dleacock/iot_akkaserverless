/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */

package iot;

import com.akkaserverless.javasdk.action.ActionCreationContext;
import iot.domain.DeviceDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** An action. */
public class DeviceStatePublishingServiceAction extends AbstractDeviceStatePublishingServiceAction {

  private static final Logger log = LoggerFactory.getLogger(DeviceStatePublishingServiceAction.class);

  public DeviceStatePublishingServiceAction(ActionCreationContext creationContext) {}

  /** Handler for "PublishDeviceStateChange". */
  @Override
  public Effect<DeviceStatePublishing.DeviceStateChange> publishDeviceStateChange(DeviceDomain.DeviceState deviceState) {

    log.info("Publishing action - Device: {} state changed to: {}", deviceState.getDeviceName(), deviceState.getCurrentValue());

    DeviceStatePublishing.DeviceStateChange response = DeviceStatePublishing.DeviceStateChange.newBuilder()
            .setState(deviceState.getCurrentValue())
            .setDeviceId(deviceState.getDeviceId())
            .build();

    return effects().reply(response);
  }
}