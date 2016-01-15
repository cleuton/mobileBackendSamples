package com.obomprogramador.gae;

/**
 * Contains the client IDs and scopes for allowed clients consuming the helloworld API.
 */
public class Constants {
  public static final String WEB_CLIENT_ID = "<< replace this with your OAuth Web Client ID >>";
  public static final String ANDROID_CLIENT_ID = "<< replace this with your OAuth Android client ID >>";
  public static final String IOS_CLIENT_ID = "<< replace this with your OAuth iOS client ID >>";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
}
