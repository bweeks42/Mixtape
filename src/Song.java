
/**
 * The Song class represents a single recorded music track.
 * A song has a length in minutes and seconds, and also a title.
 *
 * @author Blain Weeks 
 */
public class Song
{
    /**
     * The name of this song.
     */
    private String songTitle;
    /**
     * The duration of this song.
     */
    private TimeDuration duration;

    /** Construct a song from its title and duration
     * @param title the name of the song
     * @param songLength the song length in seconds
     */
    public Song(String title, int songLength) {
        this.songTitle = title;
        this.duration = new TimeDuration(songLength);
    }

    /** Construct a song from its title and duration
     * @param title the name of the song
     * @param songMinutes the minutes portion of the song length
     * @param songSeconds the seconds part of the song length
     * @pre 0 <= songMinutes <= 999 
     * @pre 0 <= songSeconds <= 59 
     */
    public Song(String title, int songMinutes, int songSeconds) {
        this.songTitle = title;
        this.duration = new TimeDuration(songMinutes, songSeconds);
    }

     /**
     * Factory method that builds a song using the given description.  If the
     * description fails to parse correctly, the song's length is 
     * set to 0:00 and the title to an empty string.
     *
     * @param description A string in the format "minutes:seconds name" where minutes
     * and seconds are integers, and name is a string. A single blank separates
     * the time from the song name.
     * @return Song created from the given description
     */
    public static Song parseSong(String description) {
        int minutes = 0;
        int seconds = 0;
        String songName = ""; 
        
        String breaks = "[ :]";
        String[] tokens = description.split(breaks);
        
        if (tokens.length == 3) {
            minutes = Integer.parseInt(tokens[0]);
            seconds = Integer.parseInt(tokens[1]);
            songName = tokens[2];
        }
        
        return new Song(songName, minutes, seconds);
    }

    /**
     * Compares this Song with the specified Object for equality.
     *
     * @param  x Object to which this Song is to be compared.
     * @return <tt>true</tt> if and only if the specified Object is a
     *  Song whose title and duration are equal to this Song.
     */
    public boolean equals(Object x) {
        boolean isEqual = false;
        
        if (x instanceof Song) {
            Song other = (Song)x;
            isEqual = this.toString().equals(other.toString());
        }
        
        return isEqual;
    }
    
    /**
     * Returns a printable representation of this song.
     *
     * @return song formatted as "MM:SS title".  Examples:<br>
     *     <code>0:55 Layla<br>
     *     1:23 Blackbird<br>
     *     11:42 Caravan<br>
     *     0:06 Shorty<br></code>
     */
    public String toString() {
        return this.duration.toString() + " " + this.songTitle;
    }

    /**
     * Returns the duration of this song.  
     *
     * @return The duration of this song.
     */
    public TimeDuration getDuration() {
        return this.duration;
    }

}