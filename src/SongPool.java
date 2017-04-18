
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Blain
 */
public class SongPool extends Vector<Song> {
    public void loadM3U(File m3uFile) {
    String input;
            String delims = "[:,]";
            String[] tokens;
            Song toAdd;
        
        try {
            Scanner sc = new Scanner(m3uFile);
            
            while(sc.hasNextLine()) {
                input = sc.nextLine();
                tokens = input.split(delims);
                if (tokens.length > 2 && "#EXTINF".equals(tokens[0])) {
                    toAdd = new Song(tokens[1], Integer.parseInt(tokens[0]));
                    this.add(toAdd);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    
    public void importFile(File songFile) {
        String input;
        String delims = "[: ]";
        String[] tokens;
        String songName = "";
        Song toAdd;
        
        try {
            Scanner sc = new Scanner(songFile);
            
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                tokens = input.split(delims);
                if (tokens.length > 3) {
                    for (int titleIndex = 2; titleIndex < tokens.length; titleIndex++) {
                        songName += tokens[titleIndex] + " ";
                    }
                    // Cut last " " from song title
                    songName = songName.substring(0, songName.length() - 1);
                    toAdd = new Song(songName, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                    this.add(toAdd);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}
