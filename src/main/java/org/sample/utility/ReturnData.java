package org.sample.utility;

import java.util.HashMap;

import static org.sample.utility.ReturnData.ReturnProp.*;

public class ReturnData {

  enum ReturnProp{
    RETURN_CODE,
    RETURN_MESSAGE,
    RETURN_DATA;
  }

  public static HashMap returnData(ReturnCode rc){
    //- return result
    HashMap hm = new HashMap();
    hm.put(RETURN_CODE, rc.getCode());
    hm.put(RETURN_MESSAGE, rc);
    return hm;
    //-
  }
  public static HashMap returnData(ReturnCode rc, Object data){
    //- return result
    HashMap hm	 = new HashMap();
    hm.put(RETURN_CODE, rc.getCode());
    hm.put(RETURN_MESSAGE, rc);
    System.out.print(data.getClass().getSimpleName());
    if("String".equals(data.getClass().getSimpleName())){
      hm.put(RETURN_DATA, data);
    }else{
      hm.put(RETURN_DATA, data);
    }
    return hm;
    //-
  }
}
