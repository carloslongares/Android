package com.example.areasyfiguras;

import android.graphics.Color;

public class ColorFigura {
	private int foto;
	private int color;
	public ColorFigura (int foto, int color) {
		this.foto= foto;
		this.color= color;
		
	}
	
	public int getFoto(){
		return foto;
	}
	
	
	public int getColor () {
		return color;
	}
	
}
