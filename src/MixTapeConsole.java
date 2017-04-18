
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The MixTapeConsole is a console-based interface for the application.  
 *
 * @author J. Dalbey
 * @version 9/2009
 */
public class MixTapeConsole {
        
    private Reader userInput;
    private Writer output;
    private MixTapeModel model;
    /**
     * Constructs the console view of the application. 
     * @param userInput a java.io.Reader from which to read the user's input,
     * usually <code>System.in</code>
     * @param output a java.io.Writer to which the output is written,
     * usually <code>writer</code>
     */
    public MixTapeConsole(Reader userInput, Writer output) {
        this.userInput = userInput;
        this.output = output;
        this.model = new MixTapeModel();
    }
    /**
     * Display the Textual user interface. Continuously prompts the user for an
     * input until the user quits.
     */
    public void run() {
        char userCommand = 0;
        do {
            this.writeSongPool();
            this.writePlaylist();
            this.writeCommandOptions();
            userCommand = this.readUserInput();
            this.respondToCommand(userCommand);
        } while(!userInput.equals("q"));
    }
    
    private void writeSongPool() {
        try {
            this.output.write("Song Pool\n");
            this.output.write(this.model.getSongPool().toString());
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    private void writePlaylist() {
        try {
            this.output.write("Playlist\n");
            this.output.write("Time used: ");
            this.output.write(this.model.getTotalTime().toString());
            this.output.write("\t");
            this.output.write("Remaining: ");
            this.output.write(this.model.getTapeLength().toString());
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    private void writeCommandOptions() {
        try {
            this.output.write("M)ove R)emove A)dd song O)pen I)mport S)ave ");
            this.output.write("C)lear T)ime set Q)uit :\n");
        }
        catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    private char readUserInput() {
        char inChar = 0;
        try {
            inChar = (char)this.userInput.read();
        } catch (IOException e) {
           System.out.println(e.toString());
        }
        return Character.toLowerCase(inChar);
    }

    private void respondToCommand(char userCommand) {
        switch(userCommand) {
            case 'm':
                this.moveSong();
                break;
            case 'r':
                this.removeSong();
                break;
            case 'a':
                this.addSong();
                break;
            case 'o':
                this.openM3U();
                break;
            case 'i':
                this.importText();
                break;
            case 's':
                this.saveFile();
                break;
            case 'c':
                this.model.clearPool();
                break;
            case 't':
                this.newTapeLength();
                break;
            case 'q':
                break;
        }
    }
    
    private void moveSong() {
        
    }
}