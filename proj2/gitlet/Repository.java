package gitlet;


import javax.swing.*;
import java.io.File;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects"); // save blob, tree, commit
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    public static final File HEADS_DIR = join(REFS_DIR, "heads");  // files named by branch pointers
    public static File HEAD  = new File(GITLET_DIR, "HEAD"); // where HEAD is
    public static File index = new File(GITLET_DIR, "index"); // index file for staging or add files


    /* TODO: fill in the rest of this class. */
    public static void init() {
        if (checkIfRepoExists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
        boolean mkdir = GITLET_DIR.mkdir() && OBJECTS_DIR.mkdir() && REFS_DIR.mkdir() && HEADS_DIR.mkdir();
        if (!mkdir) {
            System.out.println("Can't make dirs here.");
        }
        initIndex();
        initHead();

        System.out.println("Initialized empty Gitlet repository in " + GITLET_DIR);
    }

    public static void add(String filename) {
        isValidRepo();
        File file = new File(CWD, filename);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(0);
        }
        Blob blob = new Blob(file);
        Index stage = readObject(index, Index.class);
        stage.add(blob);
        writeObject(index, stage);
    }

    public static void commit(String message) {
        isValidRepo();
        Commit parent = readObject(HEAD, Commit.class);
    }

    public static void checkout(String... args) {
        isValidRepo();
    }

    private static void initIndex() {
        Index newIndex = new Index();
        writeObject(index, newIndex);
    }

    private static void initHead() {
        writeContents(HEAD, "ref: refs/heads/master");
    }

    private static boolean checkIfRepoExists() {
        return GITLET_DIR.exists() && OBJECTS_DIR.exists() && REFS_DIR.exists() && HEADS_DIR.exists();
    }

    private static void isValidRepo() {
        if (!checkIfRepoExists()) {
            System.out.println("Not a valid gitlet repo.");
            System.exit(0);
        }
    }
}
