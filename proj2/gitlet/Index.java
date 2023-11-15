package gitlet;

import javax.crypto.SealedObject;
import java.io.Serializable;
import java.util.TreeMap;

public class Index implements Serializable {
    private TreeMap<String, String> pathToBlob;

    public Index() {
        pathToBlob = new TreeMap<>();
    }

    public void add(Blob blob) {
        pathToBlob.put(blob.getFileName(), blob.getId());
    }
}
