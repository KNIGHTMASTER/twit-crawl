/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zisal.twit.core.constant;

/**
 * 
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface ApplicationConstant {
    interface Log {
        String TRIPOIN_INFO = "TRIPOIN INFO";
        String TRIPOIN_ERROR = "TRIPOIN ERROR";
    }

    interface Rest {
        String HOST_DEV = "10.210.9.84";
        String HOST_PROD = "180.250.115.49";
        int PORT_DEV = 8080;

        String BASE_URL_DEV = "http://10.210.9.84:8080/mforce-rest/ws/service";
        String BASE_URL_PROD = "http://180.250.115.49:8080/mforce-rest/ws/service";

        String AUTHORIZATION = "Authorization";
        String BASIC = "Basic";
        String ACCEPT = "Accept";
        String APPLICATION_JSON = "application/json";
        String APPLICATION_XML = "application/xml";
    }
   
    interface Database {
        String TENANT_NAME = "TRIPOIN";
        String DATABASE_NAME = "tripoin_".concat(TENANT_NAME);
        int DB_VERSION = 1;
        String ID = "id";

        interface TableUser {
            String TABLE_NAME = "mforce_user";
            String USER_NAME = "user_name";
            String LOGIN_STATUS = "login_status";
            String IS_ACTIVE = "is_active";
        }

        interface TableTestConfiguration {
            String TABLE_NAME = "dynamic_configuration";
            String SCHEDULER_INTERVAL = "scheduler_interval";
            String HOST = "host";
            String PORT = "port";
            String START_WORKING_HOUR = "start_working_hour";
            String STOP_WORKING_HOUR = "stop_working_hour";
        }
    }

    interface Status {
        interface Process {
            String PASS = "P";
            String FAILED = "F";
        }

        interface Config {
            String ACTIVE = "A";
            String DEACTIVE = "D";
        }
    }

    interface ThreadName {
        String BGP_LOGIN = "bgp login";
    }
}
