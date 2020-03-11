# Algorithm

```
binarySearch(int num, int array[], int start, int end): int  {
    if (start == end)
        if (array[start] == num)
            return start
        else
            throw NotExistsException()
    val middle = getMiddle(start, end)
    if (middle > num)
        return binarySearch(num, array, start, middle)
    else if (middle < num)
        return binarySearch(num, array, middle, end)
    else
        return middle
}
```

# Time complexities

## Line complexities
```
binarySearch(int num, int array[], int start, int end): int  { // T(n)
    if (start == end)                                          // O(1)
        if (array[start] == num)                               // O(1)
            return start                                       // O(1)
        else
            throw NotExistsException()                         // O(1)
    val middle = getMiddle(start, end)                         // O(1)
    if (middle > num)                                          // O(1)
        return binarySearch(num, array, start, middle)         // T(n/2)
    else if (middle < num)                                     // O(1)
        return binarySearch(num, array, middle, end)           // T(n/2)
    else
        return middle                                          // O(1)
}
```
So, first equation is
```
T(n) = O(1) + O(1) + O(1) + O(1) + O(1) + O(1) + T(n/2) + O(1) + T(n/2) + O(1) = 8 * O(1) + 2 * T(n/2) = O(1) + T(n/2) 
```

## Equations
```
T(1) = 1 - base condition
T(n) = 1 + T(n/2) - eqation#1
T(n/2) = 1 + T(n/4) - eqation#2
T(n/4) = 1 + T(n/8) - eqation#3
```

## Back substitution
```
Strating with eqation#1
T(n) = 1 + T(n/2) [given eqation#2]
     = 1 + (1 + T(n/4)) = 2 + T(n/4) [given eqation#3]
     = 2 + (1 + T(n/8)) = 3 + T(n/8)
       ...
     = k + T(n/2^k) [sugestion]
```
And since the function is recursive `T(1)` can also be expressed via `T(n/2^k)`, so
```
n/2^k = 1 => k = log2(n)
```
Finally,  given `k = log2(n)` and `T(n) = k + T(n/2^k)`
```
T(n) = k + T(n/2^k)
     = log2(n) + T(1) [given T(1) = 1]
     = log2(n) + 1
     = log2(n)
```

## Result
```
T(n) = log2(n)
```