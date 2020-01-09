package com.yaoh.AndroidDemo2.source_code.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yaoh on 2019/2/16
 */
public class EventBusDemo {


   public void register(){
       EventBus.getDefault().register(this);
   }

   public void unregister(){
       EventBus.getDefault().unregister(this);
   }

   public void postMessage(){

       EventBus.getDefault().post("message");
   }

}
