package com.singh.matlabfilter;

import java.math.BigDecimal;

public class Filter {
	private int PRECISION = 10;
		
	public BigDecimal[] filter(BigDecimal[] b, BigDecimal[] a, BigDecimal[] X){
		
		//Checks if these conditions are met otherwise it
		//will return the original input x
		if(a[0] != BigDecimal.ZERO && (a.length >= b.length)){
		
			int n = b.length;
			
			//Filter delay filled with zeros
			BigDecimal[] z = new BigDecimal[n];
			fillZeros(z);
	
			//The filtered signal filled with zeros
			BigDecimal[] Y = new BigDecimal[X.length];
			fillZeros(Y);
			
			//Divide b and a by first coefficient of a
			divideEach(b, a[0]);
			divideEach(a, a[0]);
			
			for(int m = 0; m < Y.length; m++){
				
				//Calculates the filtered value using
				//Y[m] = b[0] * X[m] + z[0]
				Y[m] = b[0].multiply(X[m]).add(z[0]).setScale(PRECISION, BigDecimal.ROUND_HALF_UP);
				
				for(int i= 1; i < n; i++){
					
					//Previous filter delays recalculated by
					//z[i-1] = b[i] * X[m] + z[i] - a[i] * Y[m]
					z[i-1] = b[i].multiply(X[m]).add(z[i]).subtract(a[i].multiply(Y[m])).setScale(PRECISION, BigDecimal.ROUND_HALF_UP);
					
				}
				
			}

			//Trims the last element off of filter delay
			BigDecimal[] zC = z.clone();
			z = new BigDecimal[zC.length-1];
			for(int i = 0; i < z.length; i++)
				z[i] = zC[i];
			
			//The filtered signal
			return Y;
		}
		
		//Returns original signal when conditions not met
		return X;
	}
	
	//Divides a BigDecimal array by a big decimal
	private void divideEach(BigDecimal[] array, BigDecimal divisor){
		for(int i = 0; i < array.length; i++){
			array[i] = array[i].divide(divisor).setScale(PRECISION, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	//Fills a big decimal array with zeros
	private void fillZeros(BigDecimal[] array){
		for(int i = 0; i < array.length; i++){
			array[i] = BigDecimal.ZERO;
		}
	}
	
}
