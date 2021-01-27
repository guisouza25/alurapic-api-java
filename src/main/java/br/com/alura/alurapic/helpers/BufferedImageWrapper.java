package br.com.alura.alurapic.helpers;

import java.awt.image.BufferedImage;

public class BufferedImageWrapper {

	private final String imageType;
	private final BufferedImage bufferedimage;

	public BufferedImageWrapper(String imageType, BufferedImage bufferedimage) {
		this.imageType = imageType;
		this.bufferedimage = bufferedimage;
	}

	public String getImageType() {

		return imageType;
	}

	public BufferedImage getBufferedimage() {

		return bufferedimage;
	}
}