package ipget;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteLNK {
    File file;

    boolean checkFile() {
        try {
            Path path = Paths.get("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\IPGET.link");
            Files.delete(path);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    void deleteFile() throws IOException {
        file = new File("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\IPGET.link");
        file.delete();
    }
}
