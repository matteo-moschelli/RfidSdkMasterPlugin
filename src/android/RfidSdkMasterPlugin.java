package it.anseltechnology.plugins.rfidsdkmaster;

import org.apache.cordova.CordovaPlugin;

import javax.naming.Context;

import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.anseltechnology.rfidsdkmanager.api.RfidMaster;
import it.anseltechnology.rfidsdkmanager.core.RfidDeviceModel;
//import it.anseltechnology.rfidsdkmaster.utils.Commands;
//import it.anseltechnology.rfidsdkmaster.utils.DeviceTypes;

/**
 * This class echoes a string called from JavaScript.
 */
public class RfidSdkMasterPlugin extends CordovaPlugin {

    private static final String INIT = "init";
    private static final String START_RFID = "startRfid";
    private static final String STOP_RFID = "stopRfid";

    private static final String POINT_MOBILE = "POINT_MOBILE";
    private static final String NORDIC = "NORDIC";
    private static final String CAEN = "CAEN";
    private static final String ZEBRA = "ZEBRA";

    private RfidMaster rfidInterface;
    CallbackContext myCallbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(CINIT)) {
            this.init(args);
            return true;
        } else if (action.equals(START_RFID)) {
            this.startRfidRead();
            return true;
        } else if (action.equals(STOP_RFID)) {
            this.stopRfidRead();
            return true;
        }
        return false;
    }

    /**
     * Calls the init() method of the SDK Entry-point, and initializes the required listeners.
     */
    private void init(JSONArray args) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            this.rfidInterface = new RfidMaster();

            this.rfidInterface.init(getEnum(args.getString(0)), context);

            this.rfidInterface.setOnRfidFoundListener(new it.anseltechnology.rfidsdkmanager.core.interfaces.RfidDevice.OnRfidReadResultListener() {
                @Override
                public void onRfidFound(String rfid) {
                    myCallbackContext.success(rfid);
                }

                /**
                 * Alerts that no RFID tags where found during the last reading operation.
                 */
                @Override
                public void onRfidNotFound() {
                    myCallbackContext.error("Nessun RFID rilevato");
                }

                /**
                 * Alerts that too many RFID tags were found during the last reading operation.
                 *
                 * @param tagsNumber the number of tags that was found during the last reading operation.
                 */
                @Override
                public void onRfidTooManyFound(int tagsNumber) {
                    myCallbackContext.error(String.format("Troppi tag in campo (letti %d tag.", tagsNumber));
                }

                /**
                 * Alerts that the RFID reading process has started.
                 */
                @Override
                public void onRfidReadStart() {
                    myCallbackContext.success("RFID read start");
                }

                /**
                 * Alerts that the RFID reading process has ended.
                 */
                @Override
                public void onRfidReadStop() {
                    myCallbackContext.success("RFID read stop");
                }
            });

            myCallbackContext.success("Dispositivo connesso");

        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }


    private void startRfidRead() {
        try {
            this.rfidInterface.startRfidInventory();

            myCallbackContext.success();
        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }


    private void stopRfidRead() {
        try {
            this.rfidInterface.stopRfidInventory();

            myCallbackContext.success();
        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }




    private RfidDeviceModel getEnum(String model) {
        if (type.equals(POINT_MOBILE)) {
            return RfidDeviceModel.POINT_MOBILE;
        }
        else {
            throw new Exception("Tipo di dispositivo non previsto.");
        }
    }
}
