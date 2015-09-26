/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zisal.twit.core;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * 
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class Example {

    public static void main(String [] args){
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("38zxp5aqkGaXSaAeauGDHulNr", "TyNEHQJj3dE6TV3u8hvMRkzbpGTfBiRbjfdWnBffPlh3N2h5UI");
        twitter.setOAuthAccessToken(new AccessToken("42560159-6UY0GJTNdz8EW9mUlG57rZ3Xed2fQM2B4pAgxwFr7", "Nan12CKFXQ1tNRIyflhrzefQc5TaM6thVFx2JsoFzH8ur"));
        try{
            ResponseList<Status> responseList = twitter.getUserTimeline(new Paging(1, 5));
            for(Status s: responseList){
                System.out.println("Response List ".concat(s.getText()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
