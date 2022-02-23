


// plugin 

  id 'com.google.gms.google-services'
  apply plugin: 'com.google.gms.google-services'

// Add Depandancy 

  //Firebase
    implementation 'com.google.firebase:firebase-core:18.0.0'
    implementation 'com.google.firebase:firebase-database:19.6.0'
    implementation 'com.google.firebase:firebase-ads:19.6.0'
    implementation 'com.firebase:firebase-client-android:2.5.1'
    implementation platform('com.google.firebase:firebase-bom:29.1.0')
    implementation 'com.google.firebase:firebase-analytics'

   //Google Admob
    implementation 'com.google.android.play:core:1.7.3'
    implementation 'com.google.android.gms:play-services-ads:20.5.0'
    
    

// Splash Activity 


  public static AdView adView;
  public static InterstitialAd mInterstitialAd;



private void getadd() {
        gbanner("https://smartvideoplayer-a2af6-default-rtdb.firebaseio.com/GoogleBanner");
       GInterstitial("https://smartvideoplayer-a2af6-default-rtdb.firebaseio.com/GoogleInterstrial");
    }
    
    //get banner id 
     private void gbanner(String url) {
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase(url);
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAG", "onDataChange: "+dataSnapshot.getValue(String.class));
                String id = dataSnapshot.getValue(String.class);
                Log.e("id","gbanner  "+id);
              AdsUtils.setgbanner(context,id);
            }
            
            
       //get interstrial id 
     
         private void GInterstitial(String url) {
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase(url);
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAG", "GInterstitial: "+dataSnapshot.getValue(String.class));
                String id = dataSnapshot.getValue(String.class);
                loadinterad(id);
                Log.e("id","GInterstitial  "+id);
                AdsUtils.setGInterstitial(context,id);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("TAG", "onCancelled: "+firebaseError.toString());
            }
        });
    }
         @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("TAG", "onCancelled: "+firebaseError.toString());
            }
        });
    }
    
    
    
    //load interstial 
     public static void loadinterad(String id) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context,id, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                mInterstitialAd=interstitialAd;

            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }
        });
    }
    
    //set banner 
   void setbannerad() {
        adView = new AdView(context);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(AdsUtils.getgbanner(MXVideoActivityFolderList.this));
        llbanner.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        if (adView.isLoading()) {
            Log.e("banneradd", "load");
            llbanner.setVisibility(View.VISIBLE);
        } else {
            Log.e("banneradd", "not load");
        }
    }
    
    
    //set interstaiol On button click 
     if (MXVideoActivityFolderList.mInterstitialAd != null) {
                MXVideoActivityFolderList.mInterstitialAd.show(PlayerActivity.this);
                MXVideoActivityFolderList.loadinterad(AdsUtils.getGInterstitial(PlayerActivity.this));
                MXVideoActivityFolderList.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();

                    }
                });
            }
    
    
    
    
    
  //Utils Class 
  
 
  public class AdsUtils {

    private static final String VIDEOPLAYER = "VIDEOPLAYER";



    public static  void setgbanner(Context context, String addid)
    {
        SharedPreferences.Editor edit=context.getSharedPreferences(VIDEOPLAYER,0).edit();
        edit.putString("gbanner",addid);
        edit.apply();
    }
    public static  String getgbanner(Context context)
    {
        return context.getSharedPreferences(VIDEOPLAYER,0).getString("gbanner","");
    }

    public static void setGInterstitial(Context context, String addid) {
        SharedPreferences.Editor edit = context.getSharedPreferences(VIDEOPLAYER, 0).edit();
        edit.putString(VIDEOPLAYER, addid);
        edit.apply();
    }

    public static String getGInterstitial(Context context) {
        return context.getSharedPreferences(VIDEOPLAYER, 0).getString(VIDEOPLAYER, "");
    }
}
  
  
 
    
    
