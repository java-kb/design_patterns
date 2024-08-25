# Real Examples
## JDK
* java.lang.Integer#valueOf(int) (also Boolean, Byte, Character, Short, Long and BigDecimal)
```java
    @IntrinsicCandidate
    public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
```
* 
## Spring Framework