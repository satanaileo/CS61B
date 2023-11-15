package gitlet;

import java.io.File;
import java.io.Serializable;

public class Blob implements Serializable {
    private String id;
    private byte[] contents;
    private File blobFile;
    private String fileName;

    public Blob(File file) {
        contents = Utils.readContents(file);
        id = Utils.sha1(contents);
        fileName = file.getName();
        blobFile = new File(Repository.OBJECTS_DIR, id);
        Utils.writeContents(blobFile, contents);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getBlobFile() {
        return blobFile;
    }

    public void setBlobFile(File blobFile) {
        this.blobFile = blobFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
