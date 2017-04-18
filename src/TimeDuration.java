import java.text.DecimalFormat;
/**
 * TimeDuration is an interval of time. 
 * The interval is in minutes and seconds (whole numbers).

 * Intervals greater than 60 minutes do not "roll over" to hours.  
 * 
 * @author Blain Weeks
 * @version 0.1
 */
public class TimeDuration
{
    /* duration is represented in seconds */
    private Natural duration;

     /**
      * Construct a time duration with a given initial number of seconds.
      * @param initialSeconds integer value of seconds <BR>
      * @pre 0 <= initialSeconds  <BR>
      */
    public TimeDuration(int initialSeconds) {
        this.duration = new Natural(initialSeconds);
    }

     /**
      * Construct a time duration with a given initial value for minutes
      * and seconds.
      * @param initialMinutes integer value of minutes
      * @param initialSeconds integer value of seconds <BR>
      * @pre 0 <= initialMinutes <= 999 <BR>
      * @pre 0 <= initialSeconds <= 59 <BR>
      */
    public TimeDuration(int initialMinutes, int initialSeconds) {
        this.duration = new Natural(initialSeconds + (60 * initialMinutes));
    }

    /**
     * Accessor to number of minutes.
     * @return the minutes component of this TimeDuration
     */
    public int minutes() {
        return duration.intValue() / 60;
    }

    /** Return duration as seconds.
     * @return int duration as number of seconds.
     */
    public int seconds() {
        return duration.intValue();
    }

    /* Convert a Natural to a TimeDuration */
    private static TimeDuration toTimeDuration(Natural nat) {
        TimeDuration td = new TimeDuration(nat.intValue());
        return td;
    }

    /**
     * Returns a TimeDuration whose value is <tt>(this + val)</tt>.
     *
     * @param  val value to be added to this TimeDuration.
     * @return <tt>this + val</tt>
     */
    public TimeDuration add(TimeDuration val) {
        TimeDuration td;
        td = new TimeDuration(this.seconds() + val.seconds());
        return td;
    }

    /**
     * Returns a TimeDuration whose value is <tt>(this - val)</tt>.
     *
     * @param  val value to be subtracted from this TimeDuration.
     * @return <tt>this - val</tt>
     * @throws ArithmeticException if result would be negative.
     */
    public TimeDuration subtract(TimeDuration val) {
        TimeDuration td;
        int newValue = this.seconds() - val.seconds();
        
        if(newValue < 0) {
            throw new ArithmeticException();
        }
        else {
            td = new TimeDuration(newValue);
        }
        
        return td;
    }

    /**
     * Compares this TimeDuration with the specified Object for equality.
     *
     * @param  x Object to which this TimeDuration is to be compared.
     * @return <tt>true</tt> if and only if the specified Object is a
     *  TimeDuration whose value is numerically equal to this TimeDuration.
     */
    public boolean equals(Object x) {
        boolean isEqual = false;
        
        if (x instanceof TimeDuration) {
            TimeDuration other = (TimeDuration)x;
            isEqual = (this.seconds() == other.seconds());
        }
        return isEqual;
    }

    /**
      * Return a displayable representation of this time interval.
      * @return the time in a common "MM:SS" format
      */
    public String toString() {
        return this.minutes() + 
                ":" + 
                (this.seconds() - (this.minutes() * 60));
    }
}