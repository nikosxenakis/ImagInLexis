package com.xenakis.model;

import javafx.scene.shape.Ellipse;

public class Circle {
	
	public String id;
	public Integer x;
	public Integer y;
	public Integer w;
	public Integer h;
	public Ellipse ellipse;
	
	public Circle(String id, Integer x, Integer y, Integer w, Integer h){
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
