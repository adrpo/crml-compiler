package crml.translator;

import java.io.File;
import java.nio.file.Path;

public class Utilities {

    /**
     * Remove the last .* of a name 
     * @return
     */
    public static String stripNameEnding (String name){
        // on Windows you cannot just append the path without 
        // extension because it contains the drive letter
        // so just remove that!
        if (OS.isWindows()) {
          File file = new File(name);
          Path path = file.toPath();
          path = path.getRoot().relativize(path);
          name = path.toString();
        }
        return name.substring(0, name.lastIndexOf('.'));
    }

   public static String addDirToPath (String path, String dir){
        return path + java.io.File.separator + dir;
    }

    public static final class OS
    {
       private static String OSname = null;
       public static String name()
       {
          if(OSname == null) { OSname = System.getProperty("os.name"); }
          return OSname;
       }
       public static boolean isWindows()
       {
          return name().startsWith("Windows");
       }
    }

}
