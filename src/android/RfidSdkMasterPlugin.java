package it.anseltechnology.plugins.rfidsdkmaster;

import org.apache.cordova.CordovaPlugin;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.anseltechnology.rfidsdkmanager.api.RfidMaster;

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
        myCallbackContext = callbackContext;

        if (action.equals(INIT)) {
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

            //this.rfidInterface.init(getEnum(args.getString(0)), context);

            myCallbackContext.success("Hello, " + args.getString(0) + " quasi connesso!");

        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }


    private void startRfidRead() {
        try {
            //this.rfidInterface.startRfidInventory();

            myCallbackContext.success();
        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }


    private void stopRfidRead() {
        try {
            //this.rfidInterface.stopRfidInventory();

            myCallbackContext.success();
        } catch (Exception e) {
            e.printStackTrace();
            myCallbackContext.error(e.getMessage());
        }
    }
}
