package com.singh.matlabfilter;

import java.math.BigDecimal;

public class Filter {

	public static void main(String[] args){
		
		
		
	}
	
	public BigDecimal[] filter(BigDecimal[] b, BigDecimal[] a, BigDecimal[] x){
		
		int n = b.length;
		
		BigDecimal[] z = new BigDecimal[n];
		for(int i = 0; i < z.length; i++)
			z[i] = BigDecimal.ZERO;
		
		for(int i = 0; i < b.length; i++){
			b[i] = b[i].divide(a[0]);
		}
		
		for(int i = 0; i < a.length; i++){
			a[i] = a[i].divide(a[0]);
		}
		
		BigDecimal[] Y = new BigDecimal[x.length];
		for(int i = 0; i < Y.length; i++)
			Y[i] = BigDecimal.ZERO;
		
		for(int m = 0; m < Y.length; m++){
			
			Y[m] = b[0].multiply(x[m]).add(z[0]);
			
			for(int i= 1; i < n; i++){
				
				z[i-1] = b[i].multiply(x[m]).add(z[i]).subtract(a[i].multiply(Y[m]));
				
			}
			
		}
		
		return Y;
		
	}
	
}
