/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zisal.twit.crawl.core.rest.endpoints;

import retrofit.http.POST;

/**
 * 
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */

public interface EPGetAllFriends {

    @POST("/dropcall/insert")
    void getAllFriends();
    
}
