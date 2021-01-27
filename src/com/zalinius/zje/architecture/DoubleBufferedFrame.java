package com.zalinius.zje.architecture;

import java.awt.*;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.geom.*;
import java.awt.image.BufferStrategy;

/**
 * This class extends the standard FRAME capabilities to allow double buffering when drawing to the screen.
 * This is necessary to avoid severe flickering when rendering above 10 FPS.
 * To create a screen based game, extend this class and implement the paintBuffer method to hold all rendering.
 */
public abstract class DoubleBufferedFrame extends Frame {

	private static final long serialVersionUID = 1L;

	private int bufferWidth, bufferHeight;

	private GraphicsDevice device;
	private BufferStrategy bufferStrategy;

	DoubleBufferedFrame(String title){
		super(title);
	}

	//We are overriding this to enforce buffering
	public final void update(Graphics g){
		paint(g);
	}

	public final void paint(Graphics g){
		if(bufferStrategy == null){
			BufferCapabilities cap = new BufferCapabilities(new ImageCapabilities(true), new ImageCapabilities(true), FlipContents.PRIOR);
			try {
				this.createBufferStrategy(2, cap);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.bufferStrategy = this.getBufferStrategy();
			System.out.println(bufferStrategy.getCapabilities().isPageFlipping());
			System.out.println(bufferStrategy.getCapabilities().getFlipContents());
		}

		do {

			Graphics2D bkG = (Graphics2D) bufferStrategy.getDrawGraphics();  
			bkG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    	g.setColor(Color.black);
	    	g.fillRect(20, 20, 400, 400);

			// clear the backbuffer, this could be substituted for a background
			// image or a tiled background.
			bkG.clearRect(0, 0, getWidth(), getHeight());


			// TODO: Draw your game world, or scene or anything else here.
			paintBuffer(bkG);

			// properly dispose of the backbuffer graphics object. Release resources
			// and cleanup.
			bkG.dispose();


		} while(bufferStrategy.contentsRestored());

		// flip/draw the backbuffer to the canvas component.
		bufferStrategy.show();

		// synchronize with the display refresh rate.
		Toolkit.getDefaultToolkit().sync();

	}

	public abstract void paintBuffer(Graphics2D g);

	private final void resetBuffer(){

		//		//		 always keep track of the image size
		//		bufferWidth=getSize().width;
		//		bufferHeight=getSize().height;
		//
		//		clean up the previous image
		//		if(bufferGraphics!=null){
		//			bufferGraphics.dispose();
		//			bufferGraphics=null;
		//		}
		//		if(bufferImage!=null){
		//			bufferImage.flush();
		//			bufferImage=null;
		//		}
		//		System.gc();
		//
		//		//	    create the new image with the size of the panel
		//		bufferImage=createImage(bufferWidth,bufferHeight);
		//		bufferGraphics=(Graphics2D) bufferImage.getGraphics();
		//		bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

}
