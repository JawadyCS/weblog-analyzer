
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    public int getBusiestHour() {
        int busiestHour = 0;
        int busiestValue = hourCounts[0];
        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] > hourCounts[busiestHour]) {
                busiestHour = hour;
            }
        }
        return busiestHour;
    }
    
    public int getQuietestHour() {
        int quietestHour = 0;
        int quietestValue = hourCounts[0];
        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] < hourCounts[quietestHour]) {
                quietestHour = hour;
            }
        }
        return quietestHour;
    }
    
    // Question 10: the first hour with the biggest count
    
    public int getBusiestTwoHourPeriod() {
        int busiestHour = 0;
        int busiestValue = hourCounts[0] + hourCounts[1];
        for (int hour = 1; busiestHour < hourCounts.length - 1; busiestHour++) {
            int total = hourCounts[hour] + hourCounts[hour + 1];
            if (total > busiestValue) {
                busiestValue = total;
                busiestHour = hour;
            }
        }
        return busiestHour;
    }
}
