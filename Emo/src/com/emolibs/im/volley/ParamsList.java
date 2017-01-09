
package com.emolibs.im.volley;

import android.text.TextUtils;
import java.util.ArrayList;
import com.emolibs.im.volley.ParamsList.Params;

public class ParamsList extends ArrayList<Params> {

    /**
     * 
     */
    private static final long serialVersionUID = 3546192373525578748L;

    public static class Params {

        public Params(String name, String value) {
            super();
            if (TextUtils.isEmpty(name))
                throw new RuntimeException("params key can not be null");
            this.name = name;
            if(value==null)
                value="";
            this.value = value;
        }
        String value;

        String name;
    }

}
