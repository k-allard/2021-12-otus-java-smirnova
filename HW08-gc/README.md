### До оптимизации:  
256m - Exception in thread "main" java.lang.OutOfMemoryError: Java heap space  
512m - spend msec:23558, sec:23  
896m - spend msec:22139, sec:22  
1280m - spend msec:20747, sec:20  
__1664m - spend msec:20388, sec:20__  
2048m - spend msec:20849, sec:20  
___________
### Integer -> int
256m - spend msec:5769, sec:5  
512m - spend msec:4940, sec:4  
__896m - spend msec:4494, sec:4__  
1280m - spend msec:4679, sec:4  
1664m - spend msec:4786, sec:4  
2048m - spend msec:4959, sec:4  
___________
### Объект Data создается один раз и переиспользуется:
256m - spend msec:2851, sec:2    
512m - spend msec:2631, sec:2  
896m - spend msec:2659, sec:2  
1280m - spend msec:2629, sec:2  
__1664m - spend msec:2322, sec:2__  
2048m - spend msec:2371, sec:2  
___________
### Для counter и idx типа int достаточно (было long):
256m - spend msec:2410, sec:2    
__512m - spend msec:2315, sec:2__  
896m - spend msec:2426, sec:2  
1280m - spend msec:2398, sec:2  
1664m - spend msec:2685, sec:2  
2048m - spend msec:2352, sec:2  