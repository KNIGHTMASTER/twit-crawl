/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zisal.twit.crawl.core.constant;

/**
 * 
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface GeneralConstant {



    interface Punctuation {
        String SPACE = " ";
        String COLON = ":";
        String SEMI_COLON = ";";
        String COMMA = ",";
        String QUESTION = "?";
        String UNDERSCORE = "_";
        String HYPHEN = "-";
        String SLASH = "/";
        String EMPTY = "";
    }

    interface ApplicationProperty{
        String TENANT_NAME = "Tripoin";
        String PROPERTY_FILE_NAME = "property.tripoin";
    }

    interface Network{
        public String TYPE_WIFI = "1";
        String TYPE_MOBILE = "2";
        String TYPE_NOT_CONNECTED = "0";
        String WIFI_ENABLED = "Wifi enabled";
        String MOBILE_DATA_ENABLED = "Mobile data enabled";
        String NOT_CONNECTED_TO_INTERNET = "Not connected to Internet";
        String WIFI = "WIFI";
    }

    interface PhoneState{
        String PHONE_STATE_IDLE = "IDLE";
        String PHONE_STATE_OFF_HOOK = "OFF HOOK";
        String PHONE_STATE_RINGING = "RINGING";
    }

    interface BatteryState{
        String BATTERY_INTENT_HEALTH_EXTRA_KEY = "health";
        String BATTERY_DEAD = "Dead";
        String BATTERY_OVER_VOLTAGE = "Over Voltage";
        String BATTERY_OVER_HEAT = "Over Heat";
        String BATTERY_FAILURE = "Failure";
        String BATTERY_GOOD = "Good";
        String UNKNOWN = "Unknown";
    }

    interface BroadcastMessage{
        String RESTART_SERVICE = "restart_service";
    }

    interface BundleMessage{
        String LATENCY_TCP_WORKER = "latency_tcp_worker";
    }

    interface BinaryValue{
        int MINUS_ONE = -1;
        int ZERO = 0;
        int ONE = 1;
        String MINUS_ONE_STRING = "-1";
        String ZERO_STRING = "0";
        String ONE_STRING = "1";
    }

    interface WebServiceCode{
        String SUCCESS = "200";
    }
}
