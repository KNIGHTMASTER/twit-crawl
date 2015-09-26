package com.zisal.twit.core.rest.engine;


import com.zisal.twit.core.constant.ApplicationConstant;
import com.zisal.twit.core.constant.GeneralConstant;
import com.zisal.twit.core.util.GeneralConverter;
import retrofit.RequestInterceptor;

/**
 * Created on 5/26/2015 : 10:39 AM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class CustomRequestInterceptor implements RequestInterceptor {

    private String credentials;

    public CustomRequestInterceptor(String credentials) {
        this.credentials = credentials;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(
                ApplicationConstant.Rest.AUTHORIZATION,
                ApplicationConstant.Rest.BASIC.
                        concat(GeneralConstant.Punctuation.SPACE).
                        concat(new GeneralConverter().encodeToBase64(credentials))
        );
        request.addHeader(
                ApplicationConstant.Rest.ACCEPT,
                ApplicationConstant.Rest.APPLICATION_JSON
        );
    }

}
