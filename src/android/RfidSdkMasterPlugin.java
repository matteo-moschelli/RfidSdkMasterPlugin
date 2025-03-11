package it.anseltechnology.plugins.rfidsdkmaster;

import org.apache.cordova.CordovaPlugin;

import javax.naming.Context;

import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.anseltechnology.rfidsdkmanager.api.RfidMaster;
import Commands;
import DeviceTypes;

/**
 * This class echoes a string called from JavaScript.
 */
public class RfidSdkMasterPlugin extends CordovaPlugin {

    private RfidMaster rfidInterface;
    CallbackContext myCallbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(Commands.INIT)) {
            this.init(args);
            return true;
        } else if (action.equals(Commands.START_RFID)) {
            this.startRfidRead();
            return true;
        } else if (action.equals(Commands.STOP_RFID)) {
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

            this.rfidInterface.init(DeviceTypes.getEnum(args.getString(0)), context);
        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }


    private void startRfidRead() {
        try {
            this.rfidInterface.startRfidInventory():

            myCallbackContext.success();
        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }


    private void stopRfidRead() {
        try {
            this.rfidInterface.stopRfidInventory():

            myCallbackContext.success();
        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }
}
