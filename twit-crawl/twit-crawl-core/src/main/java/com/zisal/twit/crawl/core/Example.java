/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zisal.twit.crawl.core;

import twitter4j.*;
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

        long cursor = -1;
        IDs ids = null;
        System.out.println("Listing followers's ids.");
        do {
            try {
                ids = twitter.getFollowersIDs("AchmadFauzi_212", cursor);
                for (long id : ids.getIDs()) {
                    System.out.println(id);
                    User user = twitter.showUser(id);
                    System.out.println(user.getName());
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        } while ((cursor = ids != null ? ids.getNextCursor() : 0) != 0);
    }
}
