/**
 * File IO classes is responsible for reading and writing into a file.
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileIO 
{

    private final static String inputFileName = "d://colors.txt";
    private final static String outputFileName = "d://outcome.txt";
    private FileWriter fw;
    
    public String readSettingFile() 
    {
        StringBuilder resultStringBuilder = new StringBuilder();
        if(inputFileName != null) 
        {
            try (BufferedReader br
                         = new BufferedReader(new FileReader(inputFileName))) 
                         {
                             String line;
                             while ((line = br.readLine()) != null) 
                             {
                                 resultStringBuilder.append(line).append("\n");
                              }
            } 
            catch (IOException e) 
            {
                System.out.println(e.getMessage());
            }
        }
       
        return resultStringBuilder.toString().trim();
    }

    public void writeToFile(String [] data) 
    {
        try
        {
            fw = new FileWriter(outputFileName, true);
            for(int i=0; i<data.length; i++)
            {
            fw.write(data[i]);
            if(i != data.length-1)
                fw.append(", ");
            }
            fw.write(System.getProperty("line.separator"));
  
            fw.flush();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void writeToFile(int finalScore) throws IOException
    {
        fw.append("Player final score is : " + finalScore);
        System.out.println("Game result saved in file : " + outputFileName);
        fw.flush();
        fw.close();
    }   
    
}

