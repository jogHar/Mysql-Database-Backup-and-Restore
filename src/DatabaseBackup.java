import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class DatabaseBackup {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//Backupdbtosql();
		Restoredbfromsql("ss.sql");
	}
	
	public static void Backupdbtosql() {
	    try {

	        /*NOTE: Getting path to the Jar file being executed*/
	        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
	        CodeSource codeSource = DatabaseBackup.class.getProtectionDomain().getCodeSource();
	        File jarFile = new File(codeSource.getLocation().toURI().getPath());
	        String jarDir = jarFile.getParentFile().getPath();


	        /*NOTE: Creating Database Constraints*/
	        String dbName = "alpha_school";
	        String dbUser = "root";
	        String dbPass = "root";

	        /*NOTE: Creating Path Constraints for folder saving*/
	        /*NOTE: Here the backup folder is created for saving inside it*/
	        String folderPath = jarDir + "\\jogHarbackup";

	        /*NOTE: Creating Folder if it does not exist*/
	        File f1 = new File(folderPath);
	        f1.mkdir();

	        /*NOTE: Creating Path Constraints for backup saving*/
	        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
	         String savePath = "\"" + jarDir + "\\jogHarbackup\\" + "backup.sql\"";

	        /*NOTE: Used to create a cmd command*/
	        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump -u" + dbUser + " -p" + dbPass + " " + dbName + " -r " + savePath;

	        /*NOTE: Executing the command here*/
	        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	        int processComplete = runtimeProcess.waitFor();

	        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
	        if (processComplete == 0) {
	            System.out.println("Backup Complete");
	        } else {
	            System.out.println("Backup Failure");
	        }

	    } catch (URISyntaxException | IOException | InterruptedException ex) {
	        //JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
	    	System.out.println(ex.getMessage());
	    }
	    
	}
	
	public static void Restoredbfromsql(String s) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = DatabaseBackup.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
             String dbName = "demo";
             String dbUser = "root";
             String dbPass = "root";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\jogHarbackup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
            	System.out.println("Restore Complete");
            } else {
            	System.out.println("Restore fail");
            }


        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
