package org.paradyne.lib;

/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
6   * See the LICENSE.txt file distributed with this package.
7   */

import com.jhlabs.image.DiffusionFilter;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;

import java.awt.*;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;
import java.util.Locale;

/**
 * 39 * <p/> 40 * This is the default captcha engine. It provides a sample gimpy
 * challenge that has no automated solution known. It is 41 * based on the
 * Baffle SPARC Captcha. 42 * <p/> 43 *
 * </p>
 * 44 * 45 *
 * 
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a> 46 *
 * @version 1.0 47
 */
public class DefaultGimpyEngine extends ListImageCaptchaEngine {

	/**
	 * this method should be implemented as folow :
	 * <ul>
	 * <li>First construct all the factories you want to initialize the gimpy
	 * with</li>
	 * <li>then call the this.addFactoriy method for each factory</li>
	 * </ul>
	 */ 
	
	protected void buildInitialFactories() {

		// build filters
		com.jhlabs.image.WaterFilter water = new com.jhlabs.image.WaterFilter();		
		//DiffusionFilter water=new DiffusionFilter();
		water.setAmplitude(3d);
		water.setAntialias(true);
		water.setPhase(20d);
		water.setWavelength(70d);
		
		//water.setPixels(1, 1, 1, 1, ColorModel.getRGBdefault(), new byte[4] , 1, 1);

		ImageDeformation backDef = new ImageDeformationByFilters(
				new ImageFilter[] {});
		ImageDeformation textDef = new ImageDeformationByFilters(
				new ImageFilter[] {});
		ImageDeformation postDef = new ImageDeformationByFilters(
				new ImageFilter[] { water });

		// word generator
		com.octo.captcha.component.word.wordgenerator.WordGenerator dictionnaryWords = new com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator(
				new com.octo.captcha.component.word.FileDictionary("toddlist"));
		
		// wordtoimage components
		TextPaster randomPaster = new DecoratedRandomTextPaster(new Integer(4),
				new Integer(5), new SingleColorGenerator(Color.gray),
				new TextDecorator[] { new BaffleTextDecorator(new Integer(0),
						Color.gray) });
		BackgroundGenerator back = new UniColorBackgroundGenerator(new Integer(
				200), new Integer(100), getHTMLColor("#FFFFFF"));

		FontGenerator shearedFont = new RandomFontGenerator(new Integer(60),
				new Integer(60));
		// word2image 1
		com.octo.captcha.component.image.wordtoimage.WordToImage word2image;
		System.out.println("shearedFont"+shearedFont);
		System.out.println("back"+back);
		System.out.println("randomPaster"+randomPaster);
		System.out.println("backDef"+backDef);
		System.out.println("textDef"+textDef);
		System.out.println("postDef"+postDef);
		
		System.out.println("postDef------------------"+java.util.Locale.getDefault());
		word2image = new DeformedComposedWordToImage(shearedFont, back,
				randomPaster, backDef, textDef, postDef);

		
		this.addFactory(new com.octo.captcha.image.gimpy.GimpyFactory(
				dictionnaryWords, word2image));

	}
	
	  public static Color getHTMLColor(String color) {
		 	                // HTML colors (#FFFFFF format)
	                if (color.startsWith("#")) {
		 	                        return new Color(Integer.parseInt(color.substring(1), 16));
		 	                } else {
			                        // Colors by name
		 	                        if (color.equalsIgnoreCase("black"))
			                                return Color.black;
	                       if (color.equalsIgnoreCase("grey"))
                                return Color.gray;
		                        if (color.equalsIgnoreCase("yellow"))
			                                return Color.yellow;
		                        if (color.equalsIgnoreCase("green"))
			                                return Color.green;
		 	                        if (color.equalsIgnoreCase("blue"))
		 	                                return Color.blue;
		                        if (color.equalsIgnoreCase("red"))
		                                return Color.red;
			                        if (color.equalsIgnoreCase("orange"))
			                                return Color.orange;
		                        if (color.equalsIgnoreCase("cyan"))
	                                return Color.cyan;
		                        if (color.equalsIgnoreCase("magenta"))
		                                return Color.magenta;
			                        if (color.equalsIgnoreCase("darkgray"))
		                                return Color.darkGray;
		                        if (color.equalsIgnoreCase("lightgray"))
	                                return Color.lightGray;
		                        if (color.equalsIgnoreCase("pink"))
	                                return Color.pink;
	                        if (color.equalsIgnoreCase("white"))
		                                return Color.white;
		                       
			                        throw new RuntimeException("Unsupported chart color:" + color);
		                }
		        }
}
