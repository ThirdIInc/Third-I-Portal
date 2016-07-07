package org.paradyne.lib;

/**
 * @author Lakkichand Golegaonkar
 * @date 17 Mar 2008
**/

import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

public class MyCaptchaService 
{
  // a singleton class
  private static ImageCaptchaService instance = new GenericManageableCaptchaService(new DefaultGimpyEngine() , 180, 100000);
  public static ImageCaptchaService getInstance()
  {
	  
    return instance;
  }
}
