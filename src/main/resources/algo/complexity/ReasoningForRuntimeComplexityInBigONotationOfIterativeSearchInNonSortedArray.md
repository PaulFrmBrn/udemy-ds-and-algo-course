# Algorithm

```
findBiggestNumber(int array[]): int {
    val biggest = array[0]
    for (int i = 1; i < array.length(); i++)
        if (array[i] > biggest)
            biggest = array[i]
    return biggest
}
```

# Time complexities

```
findBiggestNumber(int array[]): int {    
    val biggest = array[0]                      // O(1)
    for (int i = 1; i < array.length(); i++)    // O(n)
        if (array[i] > biggest)                 // O(1)
            biggest = array[i]                  // O(1)
    return biggest                              // O(1)
}
```
So, time complexity is
```
O(1) + O(n) + O(1) + O(1) + O(1) = O(n)
```