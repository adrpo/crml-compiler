package crml.translator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import grammar.crmlLexer;
import grammar.crmlParser;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {

  private static final Logger logger = LogManager.getLogger();	

  public static void showUsage () {
      System.out.println("usage: crml.translator.Test path/to/tests/[test.crml] [-o /path/to/output]");
      System.out.println("       provide an input directory or a test to translate all or the given .crml files to .mo files");
      System.out.println("       provide an output directory via -o on where to write the .mo files");
      System.out.println("       if no output directory via -o is given then the .mo files are generated in the current directory");
      System.out.flush();
  }


   public static void main( String[] args ) throws Exception {
    logger.trace("Args: " + args.length);
    for (int i = 0; i < args.length; i++) {
      logger.trace("Arg: [" + i + "]: " + args[i]);
    }

    String outPath = null;

    if (args.length == 0 || args.length == 2) { showUsage(); return; }
    if (args[0] == null) { showUsage(); return; }
    // check for -o directory
    if (args.length == 3) {
      if (args[1].equals("-o"))
      {
        if (args[2] == null) { showUsage(); return; }
        outPath = args[2];
      }
    }
    
    String path = new File(args[0]).getCanonicalPath();
    File file = new File ( path );

    File out_dir = new File("generated");
    out_dir.mkdir();
    logger.trace("Directory for generated .mo files: " + out_dir.getPath());

    if (file.isDirectory()) {
    logger.trace("File for test: " + path);
    String tests[] = file.list();

    for (String test : tests) {
    	if(test.endsWith(".crml")) {
    		logger.trace("Translating: " + test);
    		parse_file(path, test, out_dir.getPath(), false, false);
    	}
    }
    } else if (file.isFile()) {
      logger.trace("Directory for tests: " + path);
      out_dir.mkdir();
      logger.trace("Directory for generated .mo files: " + out_dir.getPath());
        
      logger.trace("Translating: " + file);
		  parse_file("", path, out_dir.getPath(), false, false);
    } else {
    	logger.error("Translation error: " + path +  " is not a correct path");
    }

  }

  public static void parse_file (String dir, String file, String gen_dir, Boolean testMode, Boolean generateExternal) throws IOException {
  
    try {
      String fullName = dir + java.io.File.separator + file;
      File in_file = new File(fullName);
      
      in_file.getParentFile().mkdirs();
    
      CharStream code = CharStreams.fromFileName(in_file.getAbsolutePath());
    
      crmlLexer lexer = new crmlLexer(code);
      CommonTokenStream tokens = new CommonTokenStream( lexer );
      crmlParser parser = new crmlParser( tokens );
      
      ParseTree tree = parser.definition();
      
      if (tree == null)
        logger.error("Unable to parse: " + file);
       
      List<String> external_var = new ArrayList<String>();
      crmlVisitorImpl visitor;
      if (generateExternal)
        visitor = new crmlVisitorImpl(parser, external_var);
      else
      visitor = new crmlVisitorImpl(parser);

      try {
        Value result = visitor.visit(tree);
      
      
        if (result != null) {  	
        
          File out_file = new File(gen_dir + java.io.File.separator + Utilities.stripNameEnding(file) + ".mo");  
        
          out_file.getParentFile().mkdirs();   	
        
          BufferedWriter writer = new BufferedWriter(new FileWriter(out_file));
          writer.write(result.contents);
          writer.close();
          logger.trace("Translated: " + file);

          if(generateExternal && !external_var.isEmpty()){
            File ext_file = new File(gen_dir + java.io.File.separator + Utilities.stripNameEnding(file) + "_external.txt");
            BufferedWriter ext_writer = new BufferedWriter(new FileWriter(ext_file));
            logger.trace("External variables saved in: " + ext_file);

            for(String s : external_var){
              ext_writer.write(s + "\n");
            }
            ext_writer.close();
          }
            
        }
        else
          logger.error("Unable to translate: " + file);
      } catch (ParseCancellationException e) {
        
        logger.error("Translation error: "+ e, e);
        logger.trace("The AST for the program: \n" + tree.toStringTree(parser));
        if (testMode) throw e;
      }
      catch(Exception e) {
        logger.error("Uncaught error: "+ e, e);
        logger.trace("The AST for the program: \n" + tree.toStringTree(parser));
        if (testMode) throw e;
      }
    }
    catch(Exception e)
    {
      logger.error("Uncaught error: "+ e, e);
      if (testMode) throw e;
    }

  }

}
