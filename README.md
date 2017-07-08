# Matlab's filter function implemented in Java
**I couldn't find any good guides to implementing matlab filter function so I decided to give it a try. I found Jan Simon had created the filter function in matlab so I tried to recreate it in Java.**

**[Link to Jan Simon's Implementation](https://www.mathworks.com/matlabcentral/answers/9900-use-filter-constants-to-hard-code-filter)**

### Here's his implementation in Matlab
```
function [Y, z] = myFilter(b, a, X, z)
% Author: Jan Simon, Heidelberg, (C) 2011
n    = length(a);
z(n) = 0;      % Creates zeros if input z is omitted
b = b / a(1);  % [Edited, Jan, 26-Oct-2014, normalize parameters]
a = a / a(1);
Y    = zeros(size(X));
for m = 1:length(Y)
   Y(m) = b(1) * X(m) + z(1);
   for i = 2:n
      z(i - 1) = b(i) * X(m) + z(i) - a(i) * Y(m);
   end
end
z = z(1:n - 1);
```

### Here is my implementation in Java
```
private int PRECISION = 10;		
public BigDecimal[] filter(BigDecimal[] b, BigDecimal[] a, BigDecimal[] X){
	if(a[0] != BigDecimal.ZERO && (a.length >= b.length)){
	
		int n = b.length;
		BigDecimal[] z = new BigDecimal[n];
		fillZeros(z);

		BigDecimal[] Y = new BigDecimal[X.length];
		fillZeros(Y);

		divideEach(b, a[0]);
		divideEach(a, a[0]);

		for(int m = 0; m < Y.length; m++){
			Y[m] = b[0].multiply(X[m]).add(z[0]).setScale(PRECISION, BigDecimal.ROUND_HALF_UP);
			
			for(int i= 1; i < n; i++){
				z[i-1] = b[i].multiply(X[m]).add(z[i]).subtract(a[i].multiply(Y[m])).setScale(PRECISION, BigDecimal.ROUND_HALF_UP);
			}

		}

		BigDecimal[] zC = z.clone();
		z = new BigDecimal[zC.length-1];
		for(int i = 0; i < z.length; i++)
			z[i] = zC[i];

		return Y;
	}
	return X;
}

private void divideEach(BigDecimal[] array, BigDecimal divisor){
	for(int i = 0; i < array.length; i++){
		array[i] = array[i].divide(divisor).setScale(PRECISION, BigDecimal.ROUND_HALF_UP);
	}
}

private void fillZeros(BigDecimal[] array){
	for(int i = 0; i < array.length; i++){
		array[i] = BigDecimal.ZERO;
	}
}
```
