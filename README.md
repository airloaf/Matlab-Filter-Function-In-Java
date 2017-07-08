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
