package com.zalinius.zje.architecture;

import java.awt.*;
import java.awt.geom.*;

import com.zalinius.zje.plugins.RenderSettings;

/**
 * This class extends the standard FRAME capabilities to allow double buffering when drawing to the screen.
 * This is necessary to avoid severe flickering when rendering above 10 FPS.
 * To create a screen based game, extend this class and implement the paintBuffer method to hold all rendering.
 */
public abstract class DoubleBufferedFrame extends Frame {

	private static final long serialVersionUID = 1L;
	
	private Dimension bufferDimensions;
    private Image bufferImage;
    private Graphics2D bufferGraphics;

    DoubleBufferedFrame(String title){
        super(title);
    	bufferDimensions = getSize(); 
    }

    //We are overriding this to enforce buffering
    @Override
    public final void update(Graphics g){
        paint(g);
    }

    @Override
    public final void paint(Graphics g){
        if(!bufferDimensions.equals(getSize()) || bufferImage==null || bufferGraphics==null)
            resetBuffer();

        if(bufferGraphics != null){
            //this clears the offscreen image, not the onscreen one
        	AffineTransform trans = bufferGraphics.getTransform();
        	int offSetX = (int) trans.getTranslateX();
        	int offSetY = (int) trans.getTranslateY();
            bufferGraphics.clearRect(-offSetX -50, -offSetY -50,bufferDimensions.width+50,bufferDimensions.height+50);

            //calls the paintbuffer method with
            //the offscreen graphics as a param
            paintBuffer(bufferGraphics);

            //we finally paint the offscreen image onto the onscreen image
            g.drawImage(bufferImage,0,0,this);
        }

    }

    public abstract void paintBuffer(Graphics2D g);

    private final void resetBuffer(){
        // always keep track of the image size
    	bufferDimensions = getSize();

        //    clean up the previous image
        if(bufferGraphics!=null){
            bufferGraphics.dispose();
            bufferGraphics=null;
        }
        if(bufferImage!=null){
            bufferImage.flush();
            bufferImage=null;
        }

        //    create the new image with the size of the panel
        bufferImage=createImage(bufferDimensions.width, bufferDimensions.height);
        bufferGraphics=(Graphics2D) bufferImage.getGraphics();
        bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public void accept(RenderSettings renderSettingsVisitor) {
    	renderSettingsVisitor.applySettings(this);
    }

}
