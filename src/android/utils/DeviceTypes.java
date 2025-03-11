import it.anseltechnology.rfidsdkmanager.core.RfidDeviceModel;

public class DeviceTypes {
    public static final String POINT_MOBILE = "POINT_MOBILE";
    public static final String NORDIC = "NORDIC";
    public static final String CAEN = "CAEN";
    public static final String ZEBRA = "ZEBRA";

    public static RfidDeviceModel getEnum(String type) {
        if (type.equals(POINT_MOBILE)) {
            return RfidDeviceModel.POINT_MOBILE;
        }
        else {
            throw new Exception("Tipo di dispositivo non previsto.");
        }
    }
}
