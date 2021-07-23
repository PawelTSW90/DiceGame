package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class RerollDices {
    Context context;
    UIConfig uiConfig;
    TextView rerollMessage;
    public RerollDices(Context context, UIConfig uiConfig){
        this.context = context;
        this.uiConfig = uiConfig;
        rerollMessage = ((Activity)context).findViewById(R.id.reroll_message);
        rerollMessage.setVisibility(View.INVISIBLE);
    }
    
    
    public void showMessage(){
        rerollMessage.setVisibility(View.VISIBLE);
        uiConfig.getDicesSlots()[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        
    }
}
