/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */
package iot.domain;

import com.akkaserverless.javasdk.testkit.junit.AkkaServerlessTestkitResource;
import iot.DeviceApi;
import iot.DeviceServiceClient;
import iot.Main;
import org.junit.ClassRule;
import org.junit.Test;

import static iot.domain.DeviceDomain.*;
import static java.util.concurrent.TimeUnit.*;

// Example of an integration test calling our service via the Akka Serverless proxy
// Run all test classes ending with "IntegrationTest" using `mvn verify -Pit`
public class DeviceIntegrationTest {

    /**
     * The test kit starts both the service container and the Akka Serverless proxy.
     */
    @ClassRule
    public static final AkkaServerlessTestkitResource testkit =
            new AkkaServerlessTestkitResource(Main.createAkkaServerless());

    /**
     * Use the generated gRPC client to call the service through the Akka Serverless proxy.
     */
    private final DeviceServiceClient client;

    public DeviceIntegrationTest() {
        client = DeviceServiceClient.create(testkit.getGrpcClientSettings(), testkit.getActorSystem());
    }

    @Test
    public void connectDeviceOnNonExistingEntity() throws Exception {
        final String thermostatDeviceId = "001";
        final String thermostatDeviceName = "living room thermometer";
        final DeviceType deviceType = DeviceType.THERMOSTAT;
        final String thermostatInitialState = "21C";
        final String thermostatNewState = "31C";


        final DeviceApi.ConnectDevice thermostatConnected = DeviceApi.ConnectDevice.newBuilder()
                .setId(thermostatDeviceId)
                .setName(thermostatDeviceName)
                .setType(deviceType)
                .setValue(thermostatInitialState)
                .build();
        final DeviceApi.UpdateDeviceState thermostatStateChanged = DeviceApi.UpdateDeviceState.newBuilder()
                .setId(thermostatDeviceId)
                .setValue(thermostatNewState)
                .build();

        client.connect(thermostatConnected)
                .toCompletableFuture().get(2, SECONDS);

        client.updateState(thermostatStateChanged)
                .toCompletableFuture().get(2, SECONDS);
    }
}