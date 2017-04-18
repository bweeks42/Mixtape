
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * A PlayList for a "mix tape."
 * 
 * @author 
 * @version 
 */
public class PlayList extends Vector<Song> {
    
    private TimeDuration tapeLength;
    
    public PlayList() {
        tapeLength = new TimeDuration(45, 0);
    }
    
    public void save(File saveFile) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(saveFile));
            out.write("PlayList created by Mix Tape Calculator");
            out.newLine();
            out.newLine();
            
            for (Song s : this) {
                out.write(s.toString());
                out.newLine();
            }
            
            out.newLine();
            out.write("\t" + this.getTotalTime().toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    public boolean setTapeLength(int desiredMinutes) {
        boolean changeTime = (desiredMinutes >= this.tapeLength.minutes());
        if (changeTime) {
            this.tapeLength = new TimeDuration(desiredMinutes * 60);
        }
        return changeTime;
    }
    
    public TimeDuration getTapeLength() {
        return this.tapeLength;
    }
    
    public TimeDuration getRemainingTime() {
        TimeDuration remainingTime = new TimeDuration(tapeLength.seconds());
        return remainingTime.subtract(this.getTotalTime());
    }
    
    public TimeDuration getTotalTime() {
        TimeDuration totalTime = new TimeDuration(0);
        this.forEach((s) -> {
            totalTime.add(s.getDuration());
        });
        return totalTime;
    }
}