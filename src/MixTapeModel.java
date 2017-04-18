
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MixTapeModel is the underlying data model for the MixTapeCalculator.
 * The model consists of a song "pool" of candidate songs and a playlist
 * of selected songs.  The model also knows the "capacity" available on the 
 * recording media, the current total time on the playlist, and the
 * remaining time on the playlist. 
 * No user interaction happens here.<br>
 * <b>Implementation Requirement:</b>
 * All the methods contain only a single statement except:<br> 
 * the constructor - 2 statements.<br>
 * addToMix() - 4 statements.
 * 
 * @author 
 * @version 
 */
public class MixTapeModel {
    
    private SongPool songPool;
    private PlayList playlist;
    
        
    
    /** Constructor for objects of type MixTapeModel */
    public MixTapeModel() {
        this.songPool = new SongPool();
        this.playlist = new PlayList();
    }
    /** Return the list of songs in the pool.
     * @return List list of songs
     */ 
    public Vector<Song> getSongPool() {
        return this.songPool;
    }
    /** Return the list of songs in the playlist.
     * @return List list of songs
     */ 
    public Vector<Song> getPlayList() {
        return this.playlist;
    }
    /**
     * Load the song pool from an M3U file.
     * @param m3uFile a file containing an m3u format playlist.
     */
    public void loadM3U(File m3uFile) {
        songPool.loadM3U(m3uFile);
    }
    /**
     * Load the song pool from a plain text file.
     * @param songFile a file containing a playlist in a simple text format.
     */
    public void importFile(File songFile) {
        songPool.importFile(songFile);
    }
    /**
     * Write the playlist to a text file.
     * @param saveFile the name of the file to contain the output playlist
     */
    public void save(File saveFile) {
        playlist.save(saveFile);
    }
    /** Add a new song to the song pool.
     * @param newSong Song to be added to the list of songs in the pool
     */
    public void addSong(Song newSong) {
        songPool.add(newSong);
    }
    /** Set the maximum length of time for the playlist.  
     * if desiredMinutes >= getTotalTime() sets tapelength to desiredMinutes
     *       else no effect
     * @param desiredMinutes how many minutes are available on the recording media
     * @return true if desiredMinutes >= getTotalTime(), false otherwise
     */
    public boolean setTapeLength(int desiredMinutes) {
        return playlist.setTapeLength(desiredMinutes);
    }
    /** Accessor to current tape length.
     * @return current tape length
     */
    public TimeDuration getTapeLength() {
        return playlist.getTapeLength();
    }
    
    /** Clear the songs in the song pool. */
    public void clearPool() {
        this.songPool.clear();
    }
    
    /** Move a song from the pool to the playlist.
     * @param position the zero-based index of the song to be moved.
     * @return true if the song was added to the playlist, false if adding
     * the song would have exceeded the current tapelength time. 
     */
    public boolean addToMix(int position) {
        boolean successful = songPool.get(position).getDuration().seconds() <= 
                playlist.getRemainingTime().seconds();
        if (successful) {
            playlist.add(songPool.get(position));
        }
        return successful;
    }
    
    /** Move a song from the playlist to the pool
     * @param position the zero-based index of the song to be moved.
     */ 
    public void removeFromMix(int position) {
        songPool.add(playlist.remove(position));
    }
    
    /** Determine the total time of the songs in the playlist.
     * @return TimeDuration total of songs currently in the playlist.
     */
    public TimeDuration getTotalTime() {
        return playlist.getTotalTime();
    }
    
    /** Determine the time available, i.e., total playlist time subtracted
     * from current tape length.
     *
     * @return TimeDuration remaining time
     */
    public TimeDuration getRemainingTime() {
        return playlist.getRemainingTime();
    }
}