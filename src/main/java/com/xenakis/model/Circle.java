package com.xenakis.model;

import javafx.scene.shape.Ellipse;

public class Circle {
	
	public final String id;
	public final int x;
	public final int y;
	public final int w;
	public final int h;
	public Ellipse ellipse;
	
	public Circle(String id, int x, int y, int w, int h){
		this.id = id;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.ellipse = null;
	}
	
	public void addEllipse(Ellipse ellipse){
		this.ellipse = ellipse;
	}
	
	
}
