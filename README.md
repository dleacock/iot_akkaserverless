# iot_akkaserverless

In the iot directory run "docker compose up"

To run each service run "mvn exec:exec" in each directory

To call api on Devices 

grpcurl \
  -d '{"id": "1", "value":"40C", "name":"living room thermostat", "type":"THERMOSTAT"}' \
  -plaintext localhost:9001 \
  iot.DeviceService/Connect
{
