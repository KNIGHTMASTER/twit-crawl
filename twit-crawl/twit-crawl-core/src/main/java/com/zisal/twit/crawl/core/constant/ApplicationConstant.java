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
public interface ApplicationConstant {

    interface LOG{
        String ZUNA_INFO    = "ZUNA_INFO";
        String ZUNA_ERROR   = "ZUNA_ERROR";
        String ZUNA_DEBUG   = "ZUNA_DEBUG";
        String ZUNA_WARNING = "ZUNA_WARNING";
    }

    interface Table{
        String ID               = "id";
        String CODE             = "code";
        String NAME             = "name";
        String REMARKS          = "remarks";
        String CREATED_BY       = "created_by";
        String CREATED_IP       = "created_ip";
        String CREATED_TIME     = "created_ip";
        String MODIFIED_BY      = "modified_by";
        String MODIFIED_IP      = "modified_ip";
        String MODIFIED_TIME    = "modified_time";
    }

    interface VIEW{
        String SCAFFOLD             = "/scaffold/";
        String CONTROLLER           = "/controller/";
        String REDIRECT             = "redirect:/";

        interface URL{
            String LIST             = "/list";
            String EDIT_PARAM_ID    = "/edit/{id}";
        }

        interface NAME{
            String FORM             = "_form";
            String EDIT             = "_edit";
            String LIST             = "_list";
        }

        interface ACTION {
            String EDIT             = "edit";
            String DELETE           = "delete";
        }

    }

    interface Rest{
        String BASE_URL_DEV         = "127.0.0.1";
        String AUTHORIZATION        = "Authorization";
        String BASIC                = "Basic";
        String ACCEPT               = "Accept";
        String APPLICATION_JSON     = "Application/json";
    }

}
