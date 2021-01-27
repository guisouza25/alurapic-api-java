package br.com.alura.alurapic.helpers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import javaxt.io.Image;
import javaxt.utils.Base64;

@RequestScoped
public class ImageHelper {

	public BufferedImageWrapper getImageFromInputStream(InputStream is) throws IOException {

		String format = null;
		BufferedImage bufferedimage = null;
		ImageInputStream iis = ImageIO.createImageInputStream(is);

		Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
		if (readers.hasNext()) {

			ImageReader reader = readers.next();
			format = reader.getFormatName();
			reader.setInput(iis);
			bufferedimage = reader.read(0);
		}

		return new BufferedImageWrapper(format, bufferedimage);
	}

	
	public String encodeImageToBase64(Image image, String type) {
		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ImageIO.write(imageWrapper.getBufferedimage(), imageWrapper.getImageType(), baos);
//		byte[] bytes = baos.toByteArray();
		
		String base64 = Base64.encode(image.getByteArray());
		
		return 
				"data:" + type + ";base64," + base64;
	}
	
	@SuppressWarnings("unused")
	private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
	
	public Image rotateAndResizeImage(InputStream image) throws IOException {
		
		Image newImage = new Image(image);
		
		HashMap<Integer, Object> exif = newImage.getExifTags();
		
		if(!exif.isEmpty()) {
			
			int orientation = (Integer) exif.get(0x0112);
			String desc = "";
			Double rotate = 0.0;
			switch (orientation) {
			case 1: desc = "Top, left side (Horizontal / normal)"; rotate = 0.0; break;
			case 2: desc = "Top, right side (Mirror horizontal)"; rotate = 0.0; break;
			case 3: desc = "Bottom, right side (Rotate 180)"; rotate = 180.0; break;
			case 4: desc = "Bottom, left side (Mirror vertical)"; rotate = 0.0; break;
			case 5: desc = "Left side, top (Mirror horizontal and rotate 270 CW)"; rotate = 270.0; break;
			case 6: desc = "Right side, top (Rotate 90 CW)"; rotate = 90.0; break;
			case 7: desc = "Right side, bottom (Mirror horizontal and rotate 90 CW)"; rotate = 90.0; break;
			case 8: desc = "Left side, bottom (Rotate 270 CW)"; rotate = 270.0; break;
			}
			System.out.println("Orientation: " + orientation + " -- " + desc);
			newImage.rotate(rotate);
		}
		
		Double width = (double) newImage.getWidth();
		Double height = (double) newImage.getHeight();
		
		if(width < height) {
			
			Double fator = (width / 460);
			
			width = width / fator;
			height = height / fator;
			
			newImage.resize(width.intValue(), height.intValue());
			newImage.crop(0, (int) ((height - 460) / 2), 460, 460);
		}
		
		if(height < width) {
			
			Double fator = (height / 460);
			
			width = width / fator;
			height = height / fator;
			
			newImage.resize(width.intValue(), height.intValue());
			newImage.crop( (int) (width - 460) / 2, 0, 460, 460);
		}
	
		return newImage;
		
	}
}
	
	
	
	
	