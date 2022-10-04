### До оптимизации:  
256m - Exception in thread "main" java.lang.OutOfMemoryError: Java heap space  
384m - spend msec:31495, sec:31  
512m - spend msec:23558, sec:23  
640m - spend msec:22491, sec:22  
768m - spend msec:22167, sec:22  
896m - spend msec:22139, sec:22  
1024m - spend msec:21677, sec:21  
1152m - spend msec:21361, sec:21  
1280m - spend msec:20747, sec:20  
1408m - spend msec:21525, sec:21  
1536m - spend msec:21296, sec:21  
1664m - spend msec:19933, sec:19  
1792m - spend msec:16529, sec:16  
1920m - spend msec:20518, sec:20  
2048m - spend msec:20849, sec:20  
___________
### После 2x оптимизации:
256m - spend msec:4163, sec:4  
384m - spend msec:2608, sec:2  
512m - spend msec:2631, sec:2  
640m - spend msec:2706, sec:2  
768m - spend msec:2809, sec:2  
896m - spend msec:2659, sec:2  
1024m - spend msec:2533, sec:2  
1152m -   
1280m -   
1408m - spend msec:2444, sec:2  
1536m -   
1664m -   
1792m - spend msec:2585, sec:2  
1920m - spend msec:3070, sec:3  
2048m - spend msec:2371, sec:2  
___________
### После 1x оптимизации (код):
256m - spend msec:15541, sec:15  
384m -   
512m -   
640m -   
768m - spend msec:17241, sec:17  
896m -   
1024m -   
1152m -   
1280m - spend msec:19538, sec:19  
1408m -   
1536m -   
1664m - spend msec:19428, sec:19  
1792m -   
1920m -   
2048m - spend msec:20146, sec:20  
___________
### После 1x оптимизации (integer):
256m -  spend msec:6035, sec:6  
384m -   
512m -   
640m -   
768m - spend msec:4291, sec:4  
896m -   
1024m -   
1152m -   
1280m - spend msec:4679, sec:4  
1408m -   
1536m -   
1664m - spend msec:4786, sec:4  
1792m -   
1920m -   
2048m - spend msec:5017, sec:5  